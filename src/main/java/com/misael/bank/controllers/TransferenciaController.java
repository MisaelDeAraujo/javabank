package com.misael.bank.controllers;

import com.misael.bank.entities.dto.TransferenciaRequestDto;
import com.misael.bank.entities.dto.TransferenciaResponseDto;
import com.misael.bank.services.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    @PostMapping
    public ResponseEntity<TransferenciaResponseDto> saveTransferencia(TransferenciaRequestDto dto){
        TransferenciaResponseDto dto1 = transferenciaService.saveTransferencia(dto);
        return ResponseEntity.ok().body(dto1);

    }

}
