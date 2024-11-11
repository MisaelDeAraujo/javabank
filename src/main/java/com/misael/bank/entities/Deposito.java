package com.misael.bank.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "deposito_tb")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Deposito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double valor;

    private Integer agencia;

    private Integer conta;

    private String pix;


}
