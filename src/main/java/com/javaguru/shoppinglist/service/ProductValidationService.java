package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;


import java.util.Set;

public class ProductValidationService {
    private Set<ProductValidation> validationRules;
    public ProductValidationService(Set<ProductValidation> validationRules) {
        this.validationRules = validationRules;
    }

    public void validate(Product product) {
        validationRules.forEach(s -> s.validate(product));
    }
}
