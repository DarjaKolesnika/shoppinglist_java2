package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;

import java.math.BigDecimal;

public class ProductPriceValidationRule implements ProductValidation {
    @Override
    public void validate(Product product) {
        if (product.getPrice().compareTo(BigDecimal.ZERO) < 0){
            throw new ValidationException("Product price does not match the requirements – " +
                    "it should not be less than 0");
        }
    }
}
