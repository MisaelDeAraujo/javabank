package com.misael.bank.services;

import com.misael.bank.entities.Conta;
import com.misael.bank.entities.Pessoa;
import com.misael.bank.entities.dto.RegistrarPessoaRequestDto;
import com.misael.bank.entities.dto.RegistrarPessoaResponse;
import com.misael.bank.exceptions.PessoaCadastradaException;
import com.misael.bank.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ContaService contaService;


    public RegistrarPessoaResponse savePerson(RegistrarPessoaRequestDto registrarPessoaRequestDto){
        Optional<Pessoa> findByCpf = pessoaRepository.findByCpf(registrarPessoaRequestDto.cpf());
        if(findByCpf.isPresent()){
            throw new PessoaCadastradaException();
        }else{
            Pessoa newPessoa = Pessoa.builder()
                    .nomeCompleto(registrarPessoaRequestDto.completeName())
                    .cpf(registrarPessoaRequestDto.cpf())
                    .build();

            pessoaRepository.save(newPessoa);

            Conta contaCriada =  contaService.saveConta(newPessoa);

            newPessoa.setConta(contaCriada);


            return RegistrarPessoaResponse.builder()
                    .completeName(registrarPessoaRequestDto.completeName())
                    .cpf(registrarPessoaRequestDto.cpf())
                    .agencia(contaCriada.getNumeroAgencia().toString())
                    .conta(contaCriada.getNumeroConta().toString())
                    .build();

        }
    }

    public List<RegistrarPessoaRequestDto> findAllPersons(){
        List<RegistrarPessoaRequestDto> dtos = new ArrayList<>();

        pessoaRepository.findAll().stream().forEach(person -> {
            RegistrarPessoaRequestDto dto = new RegistrarPessoaRequestDto(person.getNomeCompleto(),person.getCpf());
            dtos.add(dto);

        });

        return dtos;
    }


}
