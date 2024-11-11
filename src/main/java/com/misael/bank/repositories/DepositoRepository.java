package com.misael.bank.repositories;

import com.misael.bank.entities.Deposito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositoRepository extends JpaRepository<Integer, Deposito> {
}
