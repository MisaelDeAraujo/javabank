package com.misael.bank.services;

import com.misael.bank.entities.Chave;
import com.misael.bank.entities.Conta;
import com.misael.bank.entities.dto.ChaveRequestDto;
import com.misael.bank.entities.dto.ChaveResponseDto;
import com.misael.bank.exceptions.PessoaNaoEncontradaException;
import com.misael.bank.repositories.ChaveRepository;
import com.misael.bank.repositories.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChaveService {

    @Autowired
    private ChaveRepository chaveRepository;

    @Autowired
    private ContaRepository contaRepository;


    public ChaveResponseDto saveChave(ChaveRequestDto dto){
        Optional<Conta> findByNumeroConta = contaRepository.findByNumeroConta(dto.numeroConta());
        Optional<Conta> findByNumeroAgencia = contaRepository.findByNumeroAgencia(dto.numeroAgencia());

        if(findByNumeroAgencia.isPresent() && findByNumeroConta.isPresent()){
            Conta contaExistente = findByNumeroConta.get();

            //criar chave
            Chave chave = Chave.builder()
                    .chave(dto.chave())
                    .build();
            chaveRepository.save(chave);

            List<Chave> listaAtualizada = contaExistente.getChaves(); //pegar o que já tem em chaves
            listaAtualizada.add(chave); //acrescentar chave nova

            contaExistente.setChaves(listaAtualizada); // setar a lista em contaExistente

            contaRepository.save(contaExistente);

            return ChaveResponseDto.builder()
                    .chave(dto.chave())
                    .nomePessoa(contaExistente.getPessoa().getNomeCompleto())
                    .build();

        }else{
            throw new PessoaNaoEncontradaException();
        }

    }
}
