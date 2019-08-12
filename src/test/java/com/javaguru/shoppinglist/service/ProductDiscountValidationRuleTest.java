package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.dto.ProductDTO;
import org.assertj.core.api.Assertions;
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

public class ProductDiscountValidationRuleTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();
    @Spy
    private ProductDiscountValidationRule victim = new ProductDiscountValidationRule();
    private ProductDTO input;

    @Test
    public void shouldThrowProductValidationExceptionIfProductPriceIsUnder20() {
        input = productDTO(new BigDecimal(5));
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage("Products with price under 20 EUR cannot have a discount");
        victim.validate(input);
    }

    @Test
    public void shouldThrowProductValidationExceptionIfDiscountIsTooBig() {
        input = productDTO2(new BigDecimal(81));
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage("Product discount must not exceed 80%");
        victim.validate(input);
    }

    @Test
    public void shouldThrowProductValidationException() {
        input = productDTO(null);
        assertThatThrownBy(() -> victim.validate(input))
                .hasMessage("Product discount must be not null.");
        verify(victim, times(1)).validate(input);
    }

@Test
public void shouldValidateSuccess() {
    input = productDTO2(new BigDecimal(20));
    victim.validate(input);
}

    private ProductDTO productDTO(BigDecimal discount) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setPrice(new BigDecimal(19));
        productDTO.setDiscount(discount);
        return productDTO;
    }
    private ProductDTO productDTO2(BigDecimal discount) {
        ProductDTO productDTO2 = new ProductDTO();
        productDTO2.setPrice(new BigDecimal(50));
        productDTO2.setDiscount(discount);
        return productDTO2;
    }
}