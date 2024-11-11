package com.misael.bank.repositories;

import com.misael.bank.entities.Chave;
import com.misael.bank.entities.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta,Integer> {


    Optional<Conta> findByNumeroConta(Integer integer);

    Optional<Conta> findByNumeroAgencia(Integer integer);

}
