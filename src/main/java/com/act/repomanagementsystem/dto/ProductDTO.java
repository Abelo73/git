package com.act.repomanagementsystem.dto;


import com.act.repomanagementsystem.models.Bank;
import com.act.repomanagementsystem.models.ProductType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {


//    private Long bankId; // Store the ID of the bank
//    private Long productTypeId;
    private Bank bank;
    private ProductType productType;
    private String latestBranch;
    private String description;
    private String link;
    private String cicd;
    private String readme;
    private String receivedFrom;


}
