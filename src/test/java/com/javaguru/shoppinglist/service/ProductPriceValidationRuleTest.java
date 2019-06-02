package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ProductPriceValidationRuleTest {
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();
    private ProductPriceValidationRule victim = new ProductPriceValidationRule();
    private Product input;
    @Test
    public void shouldThrowTaskValidationExceptionIfProductPriceIsUnder0() {
        input = product(new BigDecimal(-2));
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage("Product price does not match the requirements – " +
                "it should not be less than 0");
        victim.validate(input);
    }

    private Product product(BigDecimal price) {
        Product product = new Product();
        product.setPrice(price);
        return product;
    }
}