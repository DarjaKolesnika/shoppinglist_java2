package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductPriceValidationRule implements ProductValidation {
    @Override
    public void validate(Product product) {
        checkNotNull(product);

        if (product.getPrice() == null) {
            throw new ValidationException("Product price must be not null.");
        }
        if (product.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new ValidationException("Product price does not match the requirements – " +
                    "it should not be less than 0");
        }
    }
}