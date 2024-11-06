package com.misael.bank.entities.dto;

import lombok.Builder;

import java.time.LocalDateTime;
@Builder
public record TransferenciaResponseDto(
        Double valor,
        String nomeRecebedor,
        String nomePagador,
        LocalDateTime localDateTime
) {
}
