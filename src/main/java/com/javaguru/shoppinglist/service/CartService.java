package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.converter.CartConverter;
import com.javaguru.shoppinglist.database.HibernateCartRepository;
import com.javaguru.shoppinglist.domain.Cart;
import com.javaguru.shoppinglist.dto.CartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.NoSuchElementException;

@Component
public class CartService {
    private final HibernateCartRepository cartRepository;
    private final CartConverter cartConverter;

    @Autowired
    public CartService(HibernateCartRepository cartRepository, CartConverter cartConverter) {
        this.cartRepository = cartRepository;
        this.cartConverter = cartConverter;
    }

    @Transactional
    public Long createCart(CartDTO cartDTO) {
        Cart cart = cartConverter.convert(cartDTO);
        cartRepository.save(cart);
        return cart.getId();
    }

    public CartDTO findCartById(Long id) {
        return cartRepository.findCartById(id)
                .map(cartConverter::convert)
                .orElseThrow(() -> new NoSuchElementException("Cart not found, id: " + id));
    }


    @Transactional
    public void deleteCart(Long id) {
        cartRepository.findCartById(id)
                .ifPresent(cartRepository::delete);
    }
}
