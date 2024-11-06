package com.misael.bank.entities.dto;

import lombok.Builder;

@Builder
public record ChaveRequestDto(
        Integer personId,
        String chave
) {
}
