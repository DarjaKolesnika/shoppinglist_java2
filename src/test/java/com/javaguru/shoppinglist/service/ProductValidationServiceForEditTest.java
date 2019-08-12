package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.dto.ProductDTO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

public class ProductValidationServiceForEditTest {

    @Mock
    private ProductNameValidationRule productNameValidationRule;
    @Mock
    private ProductDiscountValidationRule productDiscountValidationRule;
    @Mock
    private ProductPriceValidationRule productPriceValidationRule;

    @Captor
    private ArgumentCaptor<ProductDTO> captor;
    private ProductValidationServiceForEdit victim;
    private ProductDTO input = productDTO();

    @Before
    public void setUp() {
        Set<ProductValidation> rules = new HashSet<>();
        rules.add(productDiscountValidationRule);
        rules.add(productNameValidationRule);
        rules.add(productPriceValidationRule);
        victim = new ProductValidationServiceForEdit(rules);
    }

    @Test
    public void shouldValidate() {
        victim.validate(productDTO());
        verify(productNameValidationRule).validate(captor.capture());
        verify(productDiscountValidationRule).validate(captor.capture());
        verify(productPriceValidationRule).validate(captor.capture());

        List<ProductDTO> resultList = captor.getAllValues();
        assertThat(resultList).containsOnly(productDTO());
    }


    private ProductDTO productDTO() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(0L);
        productDTO.setDescription("TEST_DESCRIPTION");
        productDTO.setName("TEST_NAME");
        return productDTO;
    }
}