package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.database.HibernateCartRepository;
import com.javaguru.shoppinglist.domain.Cart;
import com.javaguru.shoppinglist.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.NoSuchElementException;

@Component
public class CartService {
    private final HibernateCartRepository cartRepository;
    private final CartValidationService cartValidationService;

    @Autowired
    public CartService(HibernateCartRepository cartRepository, CartValidationService cartValidationService) {
        this.cartRepository = cartRepository;
        this.cartValidationService = cartValidationService;
    }

    public Long createCart(Cart cart) {
        cartValidationService.validate(cart);
        return cartRepository.save(cart);
    }

    public Cart findCartById(Long cartId) {
        return cartRepository.findCartById(cartId)
                .orElseThrow(() -> new NoSuchElementException("Cart not found, id: " + cartId));
    }

    public void addProductToCart(Product product, Long cartId) {
        Cart cart = findCartById(cartId);
        cart.getProducts().add(product);
        cartRepository.update(cart);
    }

    public void deleteCart(Cart cart) {
        cartRepository.delete(cart);
    }
}
