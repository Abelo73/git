package com.act.repomanagementsystem.repository;

import com.act.repomanagementsystem.models.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Long> {
}
