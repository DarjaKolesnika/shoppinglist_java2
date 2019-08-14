package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.dto.ProductDTO;

public interface ProductValidation {
    void validate (ProductDTO productDTO);

    default void checkNotNull(ProductDTO productDTO) {
        if (productDTO == null) {
            throw new ValidationException("Product must not be null");
        }
    }
}
