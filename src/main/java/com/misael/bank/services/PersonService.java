package com.misael.bank.services;

import com.misael.bank.entities.Person;
import com.misael.bank.entities.dto.PersonDto;
import com.misael.bank.exceptions.UsuarioCadastradoException;
import com.misael.bank.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;


    public PersonDto savePerson(PersonDto personDto){
        Optional<Person> findByCpf = personRepository.findByCpf(personDto.cpf());
        if(findByCpf.isPresent()){
            throw new UsuarioCadastradoException();
        }else{
            Person newPerson = Person.builder()
                    .completeName(personDto.completeName())
                    .cpf(personDto.cpf())
                    .wallet(personDto.wallet())
                    .build();

            personRepository.save(newPerson);

            return personDto;

        }
    }

    public List<PersonDto> findAllPersons(){
        List<PersonDto> dtos = new ArrayList<>();

        personRepository.findAll().stream().forEach(person -> {
            PersonDto dto = new PersonDto(person.getCompleteName(),person.getCpf(), person.getWallet());
            dtos.add(dto);

        });

        return dtos;
    }


}
