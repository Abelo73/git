package com.act.repomanagementsystem.repository;

import com.act.repomanagementsystem.models.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<Gender, Long> {
}
