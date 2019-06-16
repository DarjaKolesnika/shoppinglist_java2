package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)

public class ProductPriceValidationRuleTest {
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();
    @Spy
    private ProductPriceValidationRule victim = new ProductPriceValidationRule();
    private Product input;
    @Test
    public void shouldThrowValidationExceptionIfProductPriceIsUnder0() {
        input = product(new BigDecimal(-2));
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage("Product price does not match the requirements – " +
                "it should not be less than 0");
        victim.validate(input);
    }

    @Test
    public void shouldThrowProductValidationException() {
        input = product(null);
        assertThatThrownBy(() -> victim.validate(input))
                .hasMessage("Product price must be not null.");
        verify(victim, times(1)).validate(input);
    }

    @Test
    public void shouldValidateSuccess() {
        input = product(new BigDecimal(20));
        victim.validate(input);
    }

    private Product product(BigDecimal price) {
        Product product = new Product();
        product.setPrice(price);
        return product;
    }
}