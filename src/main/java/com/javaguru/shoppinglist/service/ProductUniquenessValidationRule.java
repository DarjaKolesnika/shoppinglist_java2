package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.database.ProductRepository;
import com.javaguru.shoppinglist.domain.Product;

public class ProductUniquenessValidationRule implements ProductValidation{
    private final ProductRepository repository;

    public ProductUniquenessValidationRule(ProductRepository productRepository) {
        this.repository = productRepository;
    }

    @Override
    public void validate(Product product) {
        checkNotNull(product);
        if(repository.existsByName(product.getName())) {
            throw new ValidationException("Product must be unique, please choose another name");
        }
    }


}
