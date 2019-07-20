package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Cart;

public interface CartValidation {
    void validate(Cart cart);

    default void checkNotNull(Cart cart) {
        if (cart == null) {
            throw new ValidationException("Cart must not be null");
        }
    }
}
