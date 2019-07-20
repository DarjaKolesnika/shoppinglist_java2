package com.javaguru.shoppinglist.console.action;

import com.javaguru.shoppinglist.domain.Category;
import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.ProductService;
import com.javaguru.shoppinglist.service.ValidationException;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class CreateProductAction implements Action {

    private static final String ACTION_NAME = "Create product";

    private final ProductService productService;

    public CreateProductAction(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter product name: ");
        String name = reader.readLine();
        System.out.println("Enter product description: ");
        String description = reader.readLine();
        System.out.println("Enter product price: ");
        BigDecimal price = new BigDecimal(reader.readLine());
        System.out.println("Enter discount price: ");
        BigDecimal discount = new BigDecimal(reader.readLine());

        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setDiscount(discount.setScale(1, RoundingMode.CEILING));
        product.setPrice(price.setScale(2, RoundingMode.CEILING));

        System.out.println("Choose product category. Press 1 for FRUITS, 2 for VEGETABLES, " +
                "3 for DAIRY, 4 for ALCOHOL, 5 for MEAT.");
        int userInputCategory = Integer.parseInt(reader.readLine());
        switch (userInputCategory) {
            case 1:
                product.setCategory(Category.FRUITS);
                break;
            case 2:
                product.setCategory(Category.VEGETABLES);
                break;
            case 3:
                product.setCategory(Category.DAIRY);
                break;
            case 4:
                product.setCategory(Category.ALCOHOL);
                break;
            case 5:
                product.setCategory(Category.MEAT);
                break;
            default:
                System.out.println("Please enter the correct option");
        }

        try {
            Long response = productService.createProduct(product);
            System.out.println("Your product was successfully added to database, id: " + response);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return ACTION_NAME;
    }
}
