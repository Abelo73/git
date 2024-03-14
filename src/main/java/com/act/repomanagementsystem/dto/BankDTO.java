package com.act.repomanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankDTO {
    private Long id;
    private String bankName;
    private String bankNameDescription;
}
