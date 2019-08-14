package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.dto.ProductDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductNameValidationRule implements ProductValidation {
    @Override
    public void validate(ProductDTO productDTO) {
        final int MIN_NUMBER_OF_SYMBOLS = 3;
        final int MAX_NUMBER_OF_SYMBOLS = 25;

        checkNotNull(productDTO);

        if (productDTO.getName() == null) {
            throw new ValidationException("Product name must not be null");
        } if (productDTO.getName().length() < MIN_NUMBER_OF_SYMBOLS || productDTO.getName().length() > MAX_NUMBER_OF_SYMBOLS) {
            throw new ValidationException("Product name does not match the requirements – " +
                    "it should not contain less than 3 and more than 25 symbols");
        }
    }
}