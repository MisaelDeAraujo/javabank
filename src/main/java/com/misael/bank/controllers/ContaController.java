package com.misael.bank.controllers;

import com.misael.bank.entities.Conta;
import com.misael.bank.services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/contas")
@RestController
public class ContaController {

    @Autowired
    private ContaService contaService;


    @GetMapping
    public ResponseEntity<List<Conta>> findAll(){
        return ResponseEntity.ok().body(contaService.findAll());
    }

}
