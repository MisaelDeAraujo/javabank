package com.misael.bank.entities.dto;

import lombok.Builder;

@Builder
public record TransferenciaRequestDto(
        Double valor,
        Integer depositanteId,
        String chaveRecebedor
) {
}
