package com.javaguru.shoppinglist.console.action;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.ProductService;
import com.javaguru.shoppinglist.service.ValidationException;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class EditProductAction implements Action {
    private static final String ACTION_NAME = "Edit product";

    private final ProductService productService;

    public EditProductAction(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter product id: ");
        Long id = Long.parseLong(reader.readLine());
        Product product = productService.findProductById(id);

        System.out.println("Please select an operation. Press 1 for editing product name, 2 for editing product price, " +
                "3 for editing or adding discount, 4 for editing description, 5 for exit");
        String userInput = reader.readLine();

        System.out.println("Please enter new product name/price/discount/description");
        String newFieldInfo = reader.readLine();

        productService.editProduct(product, userInput, newFieldInfo);
        System.out.println(productService.findProductById(product.getId()));

        try {
            Long response = (productService.editProduct(product, userInput,newFieldInfo)).getId();
            System.out.println("Response: " + response);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return ACTION_NAME;
    }
}
