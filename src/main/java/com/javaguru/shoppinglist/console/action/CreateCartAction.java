package com.javaguru.shoppinglist.console.action;

import com.javaguru.shoppinglist.domain.Cart;
import com.javaguru.shoppinglist.service.CartService;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
class CreateCartAction implements Action {
    private static final String ACTION_NAME = "Create cart";

    private final CartService cartService;

    public CreateCartAction(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public void execute() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter cart name: ");
        String name = reader.readLine();

        Cart cart = new Cart();
        cart.setName(name);

        Long response = cartService.createCart(cart);
        System.out.println("Cart has been created, id: " + response);
    }

    @Override
    public String toString() {
        return ACTION_NAME ;
    }
}
