package com.misael.bank.entities.dto;

import lombok.Builder;

@Builder
public record RegistrarPessoaResponse(
        String completeName,
        String cpf,
        String agencia,
        String conta
) {
}
