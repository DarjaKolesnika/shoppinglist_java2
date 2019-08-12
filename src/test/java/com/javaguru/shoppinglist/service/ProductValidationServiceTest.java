package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;

import com.javaguru.shoppinglist.dto.ProductDTO;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)

public class ProductValidationServiceTest {
    @Mock
    private ProductUniquenessValidationRule productUniquenessValidationRule;
    @Mock
    private ProductNameValidationRule productNameValidationRule;
    @Mock
    private ProductDiscountValidationRule productDiscountValidationRule;
    @Mock
    private ProductPriceValidationRule productPriceValidationRule;


    @Captor
    private ArgumentCaptor<ProductDTO> captor;
    private ProductValidationService victim;
    private ProductDTO input = productDTO();

    @Before
    public void setUp() {
        Set<ProductValidation> rules = new HashSet<>();
        rules.add(productDiscountValidationRule);
        rules.add(productNameValidationRule);
        rules.add(productUniquenessValidationRule);
        rules.add(productPriceValidationRule);
        victim = new ProductValidationService(rules);
    }

    @Test
    public void shouldValidate() {
        victim.validate(input);
        verify(productDiscountValidationRule).validate(captor.capture());
        verify(productNameValidationRule).validate(captor.capture());
        verify(productUniquenessValidationRule).validate(captor.capture());
        verify(productPriceValidationRule).validate(captor.capture());

        List<ProductDTO> listOfResults = captor.getAllValues();
        listOfResults.forEach(product -> assertEquals(input, product));
    }

    @Test
    public void shouldValidate2() {
        victim.validate(input);
        verify(productDiscountValidationRule).validate(captor.capture());
        verify(productNameValidationRule).validate(captor.capture());
        verify(productUniquenessValidationRule).validate(captor.capture());
        verify(productPriceValidationRule).validate(captor.capture());

        List<ProductDTO> listOfResults = captor.getAllValues();
        assertThat(listOfResults).containsOnly(input);
    }

    private ProductDTO productDTO() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(0L);
        productDTO.setDescription("TEST_DESCRIPTION");
        productDTO.setName("TEST_NAME");
        return productDTO;
    }
}