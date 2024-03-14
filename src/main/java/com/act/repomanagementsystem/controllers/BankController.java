package com.act.repomanagementsystem.controllers;

import com.act.repomanagementsystem.dto.BankDTO;
import com.act.repomanagementsystem.models.Bank;
import com.act.repomanagementsystem.services.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bank")
public class BankController {
    private final BankService bankService;

    @PostMapping("/register")
    public ResponseEntity<String> registerBank(@RequestBody BankDTO bankDTO) {
        try {
            bankService.registerBank(bankDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Products registered successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Link is already taken. Please try another link");
        }
    }



    @GetMapping("/all")
    public ResponseEntity<List<Bank>> getAllBanks(){
        return bankService.getAllBanks();
    }

//    @GetMapping("/all")
//    public ResponseEntity<List<Bank>> getAllBanks() {
//        return bankService.getAllBanks();
//    }
//@GetMapping("/all")
//public ResponseEntity<List> getAllBank(){
//    return (ResponseEntity<List>) bankService.getAllBanks();
//}
    @GetMapping("/{id}")
    public ResponseEntity<Bank> getBankById(@PathVariable Long id) {
        return bankService.getBankById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBank(@PathVariable Long id, @RequestBody BankDTO bankDTO) {
        try {
            bankService.updateBank(id, bankDTO);
            return ResponseEntity.ok("Bank updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update bank.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBank(@PathVariable Long id) {
        try {
            bankService.deleteBank(id);
            return ResponseEntity.ok("Bank deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete bank.");
        }
    }


}
