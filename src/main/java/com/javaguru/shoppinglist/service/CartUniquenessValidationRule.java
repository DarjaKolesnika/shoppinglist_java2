package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.database.HibernateCartRepository;
import com.javaguru.shoppinglist.domain.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartUniquenessValidationRule implements CartValidation {
    private final HibernateCartRepository hibernateCartRepository;

    @Autowired
    public CartUniquenessValidationRule(HibernateCartRepository hibernateCartRepository) {
        this.hibernateCartRepository = hibernateCartRepository;
    }

    @Override
    public void validate(Cart cart) {
        checkNotNull(cart);
        if (hibernateCartRepository.cartExistsByName(cart.getName())) {
            throw new ValidationException("Cart must be unique, please choose another name");
        }
    }
}
