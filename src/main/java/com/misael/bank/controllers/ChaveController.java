package com.misael.bank.controllers;

import com.misael.bank.entities.Conta;
import com.misael.bank.entities.dto.ChaveRequestDto;
import com.misael.bank.entities.dto.ChaveResponseDto;
import com.misael.bank.services.ChaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chaves")
public class ChaveController {

    @Autowired
    private ChaveService chaveService;

    @PostMapping
    public ResponseEntity<ChaveResponseDto> saveChave(ChaveRequestDto dto){
        ChaveResponseDto response = chaveService.saveChave(dto);
        return ResponseEntity.ok().body(response);
    }


}
