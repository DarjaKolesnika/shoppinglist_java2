package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;

public class ProductNameValidationRule implements ProductValidation{

    @Override
    public void validate(Product product) {
        if (product.getName().length() < 3 || product.getName().length() > 25) {
            throw new ValidationException("Product name does not match the requirements – " +
                    "it should not contain less than 3 and more than 25 symbols");
        }

    }
}
