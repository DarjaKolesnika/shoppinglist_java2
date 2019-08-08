package com.javaguru.shoppinglist.converter;

import com.javaguru.shoppinglist.domain.Cart;
import com.javaguru.shoppinglist.dto.CartDTO;
import org.springframework.stereotype.Component;

@Component
public class CartToDTOConverter{
    public CartDTO convert(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        cartDTO.setName(cart.getName());
        return cartDTO;
    }

    public Cart convert (CartDTO cartDTO) {
        Cart cart = new Cart();
        cart.setId(cartDTO.getId());
        cart.setName(cartDTO.getName());
        return cart;
    }
}
