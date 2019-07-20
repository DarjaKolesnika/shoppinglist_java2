package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Cart;
import org.springframework.stereotype.Service;

@Service
public class CartValidationService {
    private final CartValidation validationRule;

    public CartValidationService(CartValidation validationRule) {
        this.validationRule = validationRule;
    }

    public void validate(Cart cart) {
        validationRule.validate(cart);
    }
}
