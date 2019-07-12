package com.javaguru.shoppinglist.console.action;

import com.javaguru.shoppinglist.service.ProductService;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class DeleteProductAction implements Action {
    private static final String ACTION_NAME = "Delete Product";

    private final ProductService productService;

    public DeleteProductAction(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter product id: ");
        Long id = Long.parseLong(reader.readLine());
        productService.deleteProduct(productService.findProductById(id));
    }

    @Override
    public String toString() {
        return ACTION_NAME;
    }
}
