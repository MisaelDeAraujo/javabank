package com.misael.bank.entities.dto;

import com.misael.bank.entities.Chave;
import lombok.Builder;

import java.util.List;
@Builder
public record ContaResponseDto(
        String completeName,
        Integer numeroConta,
        Integer numeroAgencia,
        Double saldo,
        List<Chave> chaves
) {
}
