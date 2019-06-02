package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class ProductNameValidationRuleTest {
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();
    private ProductNameValidationRule victim = new ProductNameValidationRule();
    private Product input;
    @Test
    public void shouldThrowValidationExceptionIfNameIsTooShort() {
        input = product("a");
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage("Product name does not match the requirements – " +
                "it should not contain less than 3 and more than 25 symbols");
                victim.validate(input);
    }
    @Test
    public void shouldThrowTaskValidationExceptionIfNameIsTooLong() {
        input = product("fgdfkgkdlfgjldkfjgkdfjgkdfhgjkdfhgjdfhgkdjfhgkjdgkdfkgdkfdkfgkdg");
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage("Product name does not match the requirements – " +
                "it should not contain less than 3 and more than 25 symbols");
        victim.validate(input);
    }
/*
    @Test
    public void shouldThrowValidationExceptionIfNameIsNull() {
        input = product2(null);
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage("Product name must not be null");
        victim.validate(input);
    }
    */

    private Product product(String name) {
        Product product = new Product();
        product.setName(name);
        return product;
    }

    private Product product2(String name) {
        Product product2 = new Product();
        product2.setName(name);
        return product2;
    }
}