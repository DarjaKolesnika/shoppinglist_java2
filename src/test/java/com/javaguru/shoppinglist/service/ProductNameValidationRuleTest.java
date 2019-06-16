package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)

public class ProductNameValidationRuleTest {
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();
    @Spy
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
    public void shouldThrowProductValidationExceptionIfNameIsTooLong() {
        input = product("fgdfkgkdlfgjldkfjgkdfjgkdfhgjkdfhgjdfhgkdjfhgkjdgkdfkgdkfdkfgkdg");
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage("Product name does not match the requirements – " +
                "it should not contain less than 3 and more than 25 symbols");
        victim.validate(input);
    }

    @Test
    public void shouldThrowProductValidationExceptionIfNull() {
        input = product(null);
        assertThatThrownBy(() -> victim.validate(input))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Product name must not be null");
        verify(victim, times(1)).checkNotNull(input);
    }


    @Test
    public void shouldValidateSuccess() {
        input = product("apple");
        victim.validate(input);
    }


    private Product product(String name) {
        Product product = new Product();
        product.setName(name);
        return product;
    }
}