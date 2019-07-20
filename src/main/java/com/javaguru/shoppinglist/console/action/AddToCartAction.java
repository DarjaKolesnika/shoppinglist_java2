package com.javaguru.shoppinglist.console.action;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.CartService;
import com.javaguru.shoppinglist.service.ProductService;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
class AddToCartAction implements Action {
    private static final String ACTION_NAME = "Add product to cart";

    private final ProductService productService;
    private final CartService cartService;

    AddToCartAction(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    @Override
    @Transactional
    public void execute() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter cart id:");
        Long cartId = Long.parseLong(reader.readLine());
        System.out.println("Enter product id:");
        Long productId = Long.parseLong(reader.readLine());

        Product product = productService.findProductById(productId);
        cartService.addProductToCart(product, cartId);
    }

    @Override
    public String toString() {
        return ACTION_NAME;
    }
}
