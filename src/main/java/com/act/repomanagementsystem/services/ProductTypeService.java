package com.act.repomanagementsystem.services;

import com.act.repomanagementsystem.dto.ProductTypeDTO;
import com.act.repomanagementsystem.exceptions.ProductTypeNotFoundException;
import com.act.repomanagementsystem.models.ProductType;
import com.act.repomanagementsystem.repository.ProductTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductTypeService {

    private final ProductTypeRepository productTypeRepository;
    public void registerProductType(ProductTypeDTO productTypeDTO) {
//        if (productTypeRepository.existsByLink(backOfficeDTO.getLink())) {
//            throw new LinkAlreadyInUseException("The link is already in use.");
//        }
        ProductType productType = ProductType.builder()
                .ProductTypeDescription(productTypeDTO.getProductTypeDescription())
                .productTypeName(productTypeDTO.getProductTypeName())
                .build();

        productTypeRepository.save(productType);
    }

    public ResponseEntity<List<ProductType>> getAllProductType() {
        List<ProductType> productTypes = productTypeRepository.findAll();
        return ResponseEntity.ok(productTypes);
    }

    public ProductType getProductTypeById(Long id) {
        return productTypeRepository.findById(id)
                .orElseThrow(() -> new ProductTypeNotFoundException("ProductType not found with ID: " + id));
    }

    public void updateProductType(Long id, ProductTypeDTO productTypeDTO) {
        ProductType productType = getProductTypeById(id);
        productType.setProductTypeName(productTypeDTO.getProductTypeName());
        productType.setProductTypeDescription(productType.getProductTypeDescription());
        // Update other properties as needed
        productTypeRepository.save(productType);
    }

    public void deleteProductType(Long id) {
        ProductType productType = getProductTypeById(id);
        productTypeRepository.delete(productType);
    }
}
