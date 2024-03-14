package com.act.repomanagementsystem.repository;

import com.act.repomanagementsystem.models.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Products, Long> {
    boolean existsByLink(String link);
}
