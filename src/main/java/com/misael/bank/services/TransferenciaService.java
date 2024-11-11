package com.misael.bank.services;

import com.misael.bank.entities.Chave;
import com.misael.bank.entities.Pessoa;
import com.misael.bank.entities.Transferencia;
import com.misael.bank.entities.dto.TransferenciaRequestDto;
import com.misael.bank.entities.dto.TransferenciaResponseDto;
import com.misael.bank.exceptions.PessoaNaoEncontradaException;
import com.misael.bank.repositories.ChaveRepository;
import com.misael.bank.repositories.ContaRepository;
import com.misael.bank.repositories.PessoaRepository;
import com.misael.bank.repositories.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TransferenciaService {


    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ChaveRepository chaveRepository;

    @Autowired
    private PessoaRepository pessoaRepository; //TEPORARIO


    public TransferenciaResponseDto saveTransferencia(TransferenciaRequestDto dto){

        Optional<Chave> findByChave = chaveRepository.findByChave(dto.chave_pix_ou_conta_agencia_recebedor());
        Optional<Pessoa> findPessoaById = pessoaRepository.findById(dto.depositadorId());


        if(findByChave.isPresent() && findPessoaById.isPresent()){
            Chave chaveExistente = findByChave.get();

            Pessoa depositante = findPessoaById.get();

            Pessoa recebedor = chaveExistente.getConta().getPessoa();



            depositante.getConta().setSaldo(depositante.getConta().getSaldo() - dto.valor());
            pessoaRepository.save(depositante);

            recebedor.getConta().setSaldo(recebedor.getConta().getSaldo() + dto.valor());
            pessoaRepository.save(recebedor);

            Transferencia transferencia = Transferencia.builder()
                    .valor(dto.valor())
                    .depositante(depositante.getConta())
                    .recebedor(recebedor.getConta())
                    .localDateTime(LocalDateTime.now())
                    .build();

            transferenciaRepository.save(transferencia);

            TransferenciaResponseDto transferenciaResponseDto = TransferenciaResponseDto.builder()
                    .valor(dto.valor())
                    .nomePagador(depositante.getNomeCompleto())
                    .nomeRecebedor(recebedor.getNomeCompleto())
                    .localDateTime(transferencia.getLocalDateTime())
                    .build();

            return transferenciaResponseDto;

        }else{
             throw new PessoaNaoEncontradaException();
        }
    }



}
