package com.misael.bank.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "conta_tb")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    private Integer numeroConta;

    private Integer numeroAgencia;

    private Double saldo;

    @OneToMany
    private List<Chave> chaves = new ArrayList<>();

}
