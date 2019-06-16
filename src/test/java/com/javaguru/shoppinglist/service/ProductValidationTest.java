package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


@RunWith(MockitoJUnitRunner.class)

public class ProductValidationTest {
    @Spy
    private ProductValidation victim;

    @Test
    public void shouldThrowValidationException() {
        assertThatThrownBy(() -> victim.checkNotNull(null))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Product must not be null");
    }

    @Test
    public void shouldCheckNotNull() {
        Product product = new Product();

        victim.checkNotNull(product);
    }
}