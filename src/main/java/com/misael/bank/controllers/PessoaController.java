package com.misael.bank.controllers;

import com.misael.bank.entities.dto.RegistrarPessoaRequestDto;
import com.misael.bank.entities.dto.RegistrarPessoaResponse;
import com.misael.bank.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;


    @PostMapping
    public ResponseEntity<RegistrarPessoaResponse> savePerson(RegistrarPessoaRequestDto registrarPessoaRequestDto){
        RegistrarPessoaResponse registrarPessoaResponse = pessoaService.savePerson(registrarPessoaRequestDto);
        return ResponseEntity.ok().body(registrarPessoaResponse);

    }

    @GetMapping
    public ResponseEntity<List<RegistrarPessoaRequestDto>> findPersons(){
        return ResponseEntity.ok().body(pessoaService.findAllPersons());
    }



}
