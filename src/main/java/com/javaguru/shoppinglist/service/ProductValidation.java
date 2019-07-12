package com.javaguru.shoppinglist.service;
import com.javaguru.shoppinglist.domain.Product;

public interface  ProductValidation {
    void validate (Product product);

    default void checkNotNull(Product product) {
        if (product == null) {
            throw new ValidationException("Product must not be null");
        }
    }
}
