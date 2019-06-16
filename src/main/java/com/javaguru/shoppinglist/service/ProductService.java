package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.database.ProductRepository;
import com.javaguru.shoppinglist.domain.Product;

import java.util.NoSuchElementException;
import java.util.Optional;

public class ProductService {
    private ProductRepository repository;
    private ProductValidationService validationService;

    public ProductService (ProductRepository productRepository, ProductValidationService productValidationService) {
        this.repository = productRepository;
        this.validationService = productValidationService;
    }

    public Long createProduct(Product product) {
        validationService.validate(product);
        Product createdProduct = repository.insert(product);
        return createdProduct.getId();
    }

    public Product findProductById(Long id) {
        return repository.findProductById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found, id: " + id));
    }

    public Product editProduct(Product product) {
        validationService.validate(product);
        return product;
    }

    public void deleteProduct(Long id) {
        repository.delete(id);
    }

}