package com.misael.bank.repositories;

import com.misael.bank.entities.Chave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChaveRepository extends JpaRepository<Chave, Integer> {

    Optional<Chave> findByChave(String chave);

}
