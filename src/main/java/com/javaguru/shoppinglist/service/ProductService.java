package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.converter.ProductConverter;
import com.javaguru.shoppinglist.database.RepositoryInterface;
import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.NoSuchElementException;

@Service
public class ProductService {
    private final RepositoryInterface repository;
    private final ProductValidationService validationService;
    private final ProductValidationServiceForEdit validationServiceForEdit;
    private final ProductConverter productConverter;

    public ProductService(RepositoryInterface productRepository,
                          ProductValidationService validationService, ProductConverter productConverter,
                          @Qualifier("validation for edit") ProductValidationServiceForEdit validationServiceForEdit) {
        this.repository = productRepository;
        this.validationService = validationService;
        this.productConverter = productConverter;
        this.validationServiceForEdit = validationServiceForEdit;
    }

    @Transactional
    public Long createProduct(ProductDTO productDTO) {
        validationService.validate(productDTO);
        Product product = productConverter.convert(productDTO);
        repository.insert(product);
        return product.getId();
    }

    public ProductDTO findProductById(Long id) {
        return repository.findProductById(id)
                .map(productConverter::convert)
                .orElseThrow(() -> new NoSuchElementException("Product not found, id: " + id));
    }

    public void updateProduct(ProductDTO productDTO) {
        Product product = productConverter.convert(productDTO);
        validationServiceForEdit.validate(productDTO);
        repository.update(product);
    }

    @Transactional
    public void deleteProduct(Long id) {
        repository.findProductById(id)
                .ifPresent(repository::delete);
    }
}