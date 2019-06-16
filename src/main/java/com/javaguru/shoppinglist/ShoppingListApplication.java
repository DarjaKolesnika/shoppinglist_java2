package com.javaguru.shoppinglist;

import com.javaguru.shoppinglist.console.ConsoleUI;
import com.javaguru.shoppinglist.database.ProductRepository;
import com.javaguru.shoppinglist.service.*;

import java.util.HashSet;
import java.util.Set;

class ShoppingListApplication {

    public static void main(String[] args) {

        ProductRepository repository = new ProductRepository();

        ProductDiscountValidationRule productDiscountValidationRule = new ProductDiscountValidationRule();
        ProductNameValidationRule productNameValidationRule = new ProductNameValidationRule();
        ProductPriceValidationRule productPriceValidationRule = new ProductPriceValidationRule();
        ProductUniquenessValidationRule productUniquenessValidationRule = new ProductUniquenessValidationRule(repository);

        Set<ProductValidation> rules = new HashSet<>();
        rules.add(productDiscountValidationRule);
        rules.add(productUniquenessValidationRule);
        rules.add(productPriceValidationRule);
        rules.add(productNameValidationRule);

        ProductValidationService validationService = new ProductValidationService(rules);

        ProductService productService = new ProductService(repository, validationService);

        ConsoleUI consoleUI = new ConsoleUI(productService);
        consoleUI.execute();
    }
}