package com.misael.bank.entities.dto;

import lombok.Builder;

@Builder
public record PersonDto(
        String completeName,
        String cpf,
        Double wallet
) {
}
