package com.misael.bank.services;

import com.misael.bank.entities.Conta;
import com.misael.bank.entities.Pessoa;
import com.misael.bank.entities.dto.RegistrarPessoaRequestDto;
import com.misael.bank.exceptions.PessoaCadastradaException;
import com.misael.bank.repositories.ContaRepository;
import com.misael.bank.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public Conta saveConta(Pessoa pessoa){
        int gerarAgencia = ThreadLocalRandom.current().nextInt(100000000, 1000000000);;
        int gerarConta = ThreadLocalRandom.current().nextInt(1000, 10000);;

        Conta newConta = Conta.builder()
                .pessoa(pessoa)
                .numeroConta(gerarConta)
                .numeroAgencia(gerarAgencia)
                .build();

        contaRepository.save(newConta);

        return newConta;
    }

    public List<Conta> findAll(){
        return contaRepository.findAll();
    }
}
