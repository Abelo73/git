package com.act.repomanagementsystem.repository;

import com.act.repomanagementsystem.models.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
}
