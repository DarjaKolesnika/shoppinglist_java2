package com.javaguru.shoppinglist.service;


import com.javaguru.shoppinglist.dto.ProductDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductPriceValidationRule implements ProductValidation {
    @Override
    public void validate(ProductDTO productDTO) {
        checkNotNull(productDTO);

        if (productDTO.getPrice() == null) {
            throw new ValidationException("Product price must be not null.");
        }
        if (productDTO.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new ValidationException("Product price does not match the requirements – " +
                    "it should not be less than 0");
        }
    }
}