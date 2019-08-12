package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;

import com.javaguru.shoppinglist.dto.ProductDTO;
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
    private ProductDTO input;

    @Test
    public void shouldThrowValidationExceptionIfNameIsTooShort() {
        input = productDTO("a");
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage("Product name does not match the requirements – " +
                "it should not contain less than 3 and more than 25 symbols");
        victim.validate(input);
    }

    @Test
    public void shouldThrowProductValidationExceptionIfNameIsTooLong() {
        input = productDTO("fgdfkgkdlfgjldkfjgkdfjgkdfhgjkdfhgjdfhgkdjfhgkjdgkdfkgdkfdkfgkdg");
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage("Product name does not match the requirements – " +
                "it should not contain less than 3 and more than 25 symbols");
        victim.validate(input);
    }

    @Test
    public void shouldThrowProductValidationExceptionIfNull() {
        input = productDTO(null);
        assertThatThrownBy(() -> victim.validate(input))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Product name must not be null");
        verify(victim, times(1)).checkNotNull(input);
    }


    @Test
    public void shouldValidateSuccess() {
        input = productDTO("apple");
        victim.validate(input);
    }


    private ProductDTO productDTO(String name) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(name);
        return productDTO;
    }
}