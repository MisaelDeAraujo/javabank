package com.misael.bank.services;

import com.misael.bank.entities.Chave;
import com.misael.bank.entities.Person;
import com.misael.bank.entities.dto.ChaveRequestDto;
import com.misael.bank.entities.dto.ChaveResponseDto;
import com.misael.bank.exceptions.UsuarioNaoEncontradoException;
import com.misael.bank.repositories.ChaveRepository;
import com.misael.bank.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChaveService {

    @Autowired
    private ChaveRepository chaveRepository;

    @Autowired
    private PersonRepository personRepository;

    public ChaveResponseDto saveChave(ChaveRequestDto dto){
        Optional<Person> findPerson = personRepository.findById(dto.personId());

        if(findPerson.isPresent()){
            Person person = findPerson.get();

            person.getChaves().add(dto.chave());

            List<String> listaAtualizada = person.getChaves();

            person.setChaves(listaAtualizada);

            Chave chave = Chave.builder()
                    .chave(dto.chave())
                    .person(person)
                    .build();

            chaveRepository.save(chave);
            personRepository.save(person);

            return ChaveResponseDto.builder()
                    .chave(dto.chave())
                    .nomePessoa(person.getCompleteName())
                    .build();

        }else{
            throw new UsuarioNaoEncontradoException();
        }

    }
}
