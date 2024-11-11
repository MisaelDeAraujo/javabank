package com.misael.bank.entities.dto;

import lombok.Builder;

@Builder
public record TransferenciaRequestDto(
        Double valor,
        String chave_pix_ou_conta_agencia_recebedor,
        Integer depositadorId
) {
}
