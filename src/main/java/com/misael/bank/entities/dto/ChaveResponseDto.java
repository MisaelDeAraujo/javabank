package com.misael.bank.entities.dto;

import lombok.Builder;

@Builder
public record ChaveResponseDto(
        String chave,
        String nomePessoa
) {
}
