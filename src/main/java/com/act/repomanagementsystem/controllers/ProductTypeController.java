package com.act.repomanagementsystem.controllers;

import com.act.repomanagementsystem.dto.ProductTypeDTO;
import com.act.repomanagementsystem.models.ProductType;
import com.act.repomanagementsystem.services.ProductTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/productType")
public class ProductTypeController {
    private final ProductTypeService productTypeService;

    @PostMapping("/register")
    public ResponseEntity<String> registerProductType(@RequestBody ProductTypeDTO productTypeDTO) {
        try {
            productTypeService.registerProductType(productTypeDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("ProductType registered successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Link is already taken. Please try another link");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductType>> getAllProductType(){
        return productTypeService.getAllProductType();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductType> getProductTypeById(@PathVariable Long id) {
        ProductType productType = productTypeService.getProductTypeById(id);
        if (productType != null) {
            return ResponseEntity.ok(productType);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProductType(@PathVariable Long id, @RequestBody ProductTypeDTO productTypeDTO) {
        try {
            productTypeService.updateProductType(id, productTypeDTO);
            return ResponseEntity.ok("ProductType updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating ProductType: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductType(@PathVariable Long id) {
        try {
            productTypeService.deleteProductType(id);
            return ResponseEntity.ok("ProductType deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting ProductType: " + e.getMessage());
        }
    }

}
