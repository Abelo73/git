package com.act.repomanagementsystem.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductTypeDTO {
    private Long id;
    private String productTypeName;
    private String ProductTypeDescription;


}
