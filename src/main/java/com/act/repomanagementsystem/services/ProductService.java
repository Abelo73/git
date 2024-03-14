package com.act.repomanagementsystem.services;


import com.act.repomanagementsystem.dto.ProductDTO;
import com.act.repomanagementsystem.exceptions.BankNotFoundException;
import com.act.repomanagementsystem.exceptions.LinkAlreadyInUseException;
import com.act.repomanagementsystem.exceptions.ProductTypeNotFoundException;
import com.act.repomanagementsystem.models.Bank;
import com.act.repomanagementsystem.models.ProductType;
import com.act.repomanagementsystem.models.Products;
import com.act.repomanagementsystem.repository.BankRepository;
import com.act.repomanagementsystem.repository.ProductRepository;
import com.act.repomanagementsystem.repository.ProductTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final BankRepository bankRepository;
    private final ProductTypeRepository productTypeRepository;

//    public void registerProduct(ProductDTO productDTO) {
//        if (productRepository.existsByLink(productDTO.getLink())) {
//            throw new LinkAlreadyInUseException("The link is already in use.");
//        }
//        Products products = Products.builder()
//                .bankName(productDTO.getBankName())
//                .latestBranch(productDTO.getLatestBranch())
//                .description(productDTO.getDescription())
//                .link(productDTO.getLink())
//                .cicd(productDTO.getCicd())
//                .readme(productDTO.getReadme())
//                .receivedFrom(productDTO.getReceivedFrom())
//                .build();
//
//        productRepository.save(products);
//    }

//    public void registerProduct(ProductDTO productDTO) {
//        if (productRepository.existsByLink(productDTO.getLink())) {
//            throw new LinkAlreadyInUseException("The link is already in use.");
//        }
//        Products products = new Products();
//        products.setBankName(productDTO.getBankDTO().getBankName());
//        products.setProductName(productDTO.getProductTypeDTO().getProductTypeName());
//        products.setLatestBranch(productDTO.getLatestBranch());
//        products.setDescription(productDTO.getDescription());
//        products.setLink(productDTO.getLink());
//        products.setCicd(productDTO.getCicd());
//        products.setReadme(productDTO.getReadme());
//        products.setReceivedFrom(productDTO.getReceivedFrom());
//
//        productRepository.save(products);
//    }

//    public void registerProduct(ProductDTO productDTO) {
//        // Check if the bank and product type IDs exist
//        Bank bank = bankRepository.findById(productDTO.getBank().getId())
//                .orElseThrow(() -> new BankNotFoundException("Bank not found with ID: " + productDTO.getBank().getId()));
//
//        ProductType productType = productTypeRepository.findById(productDTO.getProductType().getId())
//                .orElseThrow(() -> new ProductTypeNotFoundException("Product type not found with ID: " + productDTO.getProductType().getId()));
//
//        // Proceed with product registration
//        if (productRepository.existsByLink(productDTO.getLink())) {
//            throw new LinkAlreadyInUseException("The link is already in use.");
//        }
//
//        Products products = new Products();
//        products.setBank(bank);
//        products.setProductType(productType);
//        products.setLatestBranch(productDTO.getLatestBranch());
//        products.setDescription(productDTO.getDescription());
//        products.setLink(productDTO.getLink());
//        products.setCicd(productDTO.getCicd());
//        products.setReadme(productDTO.getReadme());
//        products.setReceivedFrom(productDTO.getReceivedFrom());
//
//        productRepository.save(products);
//    }

//    public void registerProduct(ProductDTO productDTO) {
//        // Check if the bank and product type IDs exist
//        Long bankId = productDTO.getBank().getId();
//        Long productTypeId = productDTO.getProductType().getId();
//
//        Bank bank = bankRepository.findById(bankId)
//                .orElseThrow(() -> new BankNotFoundException("Bank not found with ID: " + bankId));
//
//        ProductType productType = productTypeRepository.findById(productTypeId)
//                .orElseThrow(() -> new ProductTypeNotFoundException("Product type not found with ID: " + productTypeId));
//
//        // Proceed with product registration
//        if (productRepository.existsByLink(productDTO.getLink())) {
//            throw new LinkAlreadyInUseException("The link is already in use.");
//        }
//
//        Products products = new Products();
//        products.setBank(bank);
//        products.setProductType(productType);
//        products.setLatestBranch(productDTO.getLatestBranch());
//        products.setDescription(productDTO.getDescription());
//        products.setLink(productDTO.getLink());
//        products.setCicd(productDTO.getCicd());
//        products.setReadme(productDTO.getReadme());
//        products.setReceivedFrom(productDTO.getReceivedFrom());
//
//        productRepository.save(products);
//    }

    public void registerProduct(ProductDTO productDTO) {
        // Fetch the Bank entity using the bankId
        Bank bank = bankRepository.findById(productDTO.getBank().getId())
                .orElseThrow(() -> new BankNotFoundException("Bank not found with ID: " + productDTO.getBank()));

        // Fetch the ProductType entity using the productTypeId
        ProductType productType = productTypeRepository.findById(productDTO.getProductType().getId())
                .orElseThrow(() -> new ProductTypeNotFoundException("Product type not found with ID: " + productDTO.getProductType()));

        // Proceed with product registration
        if (productRepository.existsByLink(productDTO.getLink())) {
            throw new LinkAlreadyInUseException("The link is already in use.");
        }

        Products products = new Products();
        products.setBank(bank);
        products.setProductType(productType);
        products.setLatestBranch(productDTO.getLatestBranch());
        products.setDescription(productDTO.getDescription());
        products.setLink(productDTO.getLink());
        products.setCicd(productDTO.getCicd());
        products.setReadme(productDTO.getReadme());
        products.setReceivedFrom(productDTO.getReceivedFrom());

        productRepository.save(products);
    }

    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }

    public Products getProductsById(Long id) {
        return productRepository.findById(id)
                .orElse(null);
    }


    public void deleteProduct(Long id) {
    }


}
