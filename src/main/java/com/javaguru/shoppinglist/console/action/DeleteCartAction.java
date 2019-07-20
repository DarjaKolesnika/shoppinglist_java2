package com.javaguru.shoppinglist.console.action;

import com.javaguru.shoppinglist.service.CartService;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class DeleteCartAction implements Action {
    private static final String ACTION_NAME = "Delete cart";

    private final CartService cartService;

    public DeleteCartAction(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public void execute() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter cart id: ");
        Long id = Long.parseLong(reader.readLine());
        cartService.deleteCart(cartService.findCartById(id));
    }

    @Override
    public String toString() {
        return ACTION_NAME;
    }
}