package com.javaguru.shoppinglist.domain;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ProductTest {

    @Test
    public void printInfoAboutProduct() {
        String result = product().printInfoAboutProduct();
        String expectedResult = "Actual information(name: apple, category: null, description: good, " +
                "regular price: 100, discount: 50%, current price: 50.00)";
        assertEquals(result, expectedResult);
    }
    private Product product() {
        Product product = new Product();
        product.setName("apple");
        product.setDiscount(new BigDecimal(50));
        product.setPrice(new BigDecimal(100));
        product.setDescription("good");
        return product;
    }

}