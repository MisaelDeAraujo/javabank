package com.misael.bank.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "person_tb")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String nomeCompleto;
    @Column
    private String cpf;
    @Column
    private String email;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "conta_id")
    private Conta conta;



}
