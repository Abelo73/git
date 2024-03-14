package com.act.repomanagementsystem.controllers;

import com.act.repomanagementsystem.dto.ProductDTO;
import com.act.repomanagementsystem.models.Products;
import com.act.repomanagementsystem.services.AuthenticationFacade;
import com.act.repomanagementsystem.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;
    private final AuthenticationFacade authenticationFacade;

    @PostMapping("/register")
    public ResponseEntity<String> registerProduct(@RequestBody ProductDTO productDTO) {
        try {
            productService.registerProduct(productDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Products registered successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Link is already taken. Please try another link");
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<Products>> getAllProducts() {
        List<Products> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Products> getProductsById(@PathVariable Long id) {
        Products products = productService.getProductsById(id);
        if (products != null) {
            return ResponseEntity.ok(products);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        String currentUserRole = authenticationFacade.getLoggedInUserRole();
        if (!"ADMIN".equals(currentUserRole)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only ADMIN can delete Products.");
        }

        try {
            productService.deleteProduct(id);
            return ResponseEntity.ok("Products deleted successfully.");
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
