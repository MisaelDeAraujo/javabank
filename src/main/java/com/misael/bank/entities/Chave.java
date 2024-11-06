package com.misael.bank.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "chaves_tb")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Chave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String chave;
    @ManyToOne
    private Person person;


}
