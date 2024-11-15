package com.misael.bank.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "transferencia_tb")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double valor;

    @ManyToOne
    private Conta depositante;
    @ManyToOne
    private Conta recebedor;

    private LocalDateTime localDateTime;

}
