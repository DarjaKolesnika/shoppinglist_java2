package com.javaguru.shoppinglist.console.action;

import com.javaguru.shoppinglist.domain.Cart;
import com.javaguru.shoppinglist.service.CartService;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class FindCartAction implements Action{
    private static final String ACTION_NAME = "Find cart by ID";
    private final CartService cartService;

    public FindCartAction(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public void execute() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter cart id: ");
        Long id = Long.parseLong(reader.readLine());
        Cart cart = cartService.findCartById(id);
        System.out.println("Cart has been found, id: " + cart.getId());
    }

    @Override
    public String toString() {
        return ACTION_NAME;
    }
}
