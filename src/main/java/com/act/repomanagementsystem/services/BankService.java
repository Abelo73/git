package com.act.repomanagementsystem.services;

import com.act.repomanagementsystem.dto.BankDTO;
import com.act.repomanagementsystem.exceptions.BankNotFoundException;
import com.act.repomanagementsystem.models.Bank;
import com.act.repomanagementsystem.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BankService {

    private final BankRepository bankRepository;
    public void registerBank(BankDTO bankDTO) {
//        if (productTypeRepository.existsByLink(backOfficeDTO.getLink())) {
//            throw new LinkAlreadyInUseException("The link is already in use.");
//        }
        Bank bank = Bank.builder()
                .bankName(bankDTO.getBankName())
                .bankNameDescription(bankDTO.getBankNameDescription())
                .build();
        bankRepository.save(bank);
    }


//    public ResponseEntity<List<Bank>> getAllBanks() {
//        List<Bank> banks = bankRepository.findAll();
//        return ResponseEntity.ok(banks);
//    }

    public ResponseEntity<Bank> getBankById(Long id) {
        Optional<Bank> optionalBank = bankRepository.findById(id);
        if (optionalBank.isPresent()) {
            return ResponseEntity.ok(optionalBank.get());
        } else {
            throw new BankNotFoundException("Bank not found with ID: " + id);
        }
    }

    public void updateBank(Long id, BankDTO bankDTO) {
        Bank bank = bankRepository.findById(id)
                .orElseThrow(() -> new BankNotFoundException("Bank not found with ID: " + id));
        bank.setBankName(bankDTO.getBankName());
        bank.setBankNameDescription(bankDTO.getBankNameDescription());
        bankRepository.save(bank);
    }

    public void deleteBank(Long id) {
        Bank bank = bankRepository.findById(id)
                .orElseThrow(() -> new BankNotFoundException("Bank not found with ID: " + id));
        bankRepository.delete(bank);
    }

    public ResponseEntity<List<Bank>> getAllBanks() {
       List<Bank> banks = bankRepository.findAll();
       return ResponseEntity.ok(banks);
    }
}
