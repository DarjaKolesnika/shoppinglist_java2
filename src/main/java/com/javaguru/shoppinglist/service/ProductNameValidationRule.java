package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;

public class ProductNameValidationRule implements ProductValidation {
    @Override
    public void validate(Product product) {
        final int MIN_NUMBER_OF_SYMBOLS = 3;
        final int MAX_NUMBER_OF_SYMBOLS = 25;

        checkNotNull(product);

        if (product.getName() == null) {
            throw new ValidationException("Product name must not be null");
        } if (product.getName().length() < MIN_NUMBER_OF_SYMBOLS || product.getName().length() > MAX_NUMBER_OF_SYMBOLS) {
            throw new ValidationException("Product name does not match the requirements – " +
                    "it should not contain less than 3 and more than 25 symbols");
        }
    }
}