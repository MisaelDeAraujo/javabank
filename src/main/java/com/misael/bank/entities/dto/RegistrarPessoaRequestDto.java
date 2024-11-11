package com.misael.bank.entities.dto;

public record RegistrarPessoaRequestDto(
        String completeName,
        String cpf
) {
}
