package com.misael.bank.controllers;

import com.misael.bank.entities.dto.PersonDto;
import com.misael.bank.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService personService;


    @PostMapping
    public ResponseEntity<PersonDto> savePerson(PersonDto personDto){
        PersonDto personDto1 = personService.savePerson(personDto);
        return ResponseEntity.ok().body(personDto1);

    }

    @GetMapping
    public ResponseEntity<List<PersonDto>> findPersons(){
        return ResponseEntity.ok().body(personService.findAllPersons());
    }



}
