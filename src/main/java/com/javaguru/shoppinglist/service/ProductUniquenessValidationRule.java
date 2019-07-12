package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.database.RepositoryInterface;
import com.javaguru.shoppinglist.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductUniquenessValidationRule implements ProductValidation {
    private final RepositoryInterface repository;

    @Autowired
    public ProductUniquenessValidationRule(RepositoryInterface productRepository) {
        this.repository = productRepository;
    }

    @Override
    public void validate(Product product) {
        checkNotNull(product);
        if (repository.existsByName(product.getName())) {
            throw new ValidationException("Product must be unique, please choose another name");
        }
    }
}
