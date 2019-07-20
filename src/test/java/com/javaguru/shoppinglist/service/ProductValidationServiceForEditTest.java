package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

public class ProductValidationServiceForEditTest {

    @Mock
    private ProductNameValidationRule productNameValidationRule;
    @Mock
    private ProductDiscountValidationRule productDiscountValidationRule;
    @Mock
    private ProductPriceValidationRule productPriceValidationRule;


    @Captor
    private ArgumentCaptor<Product> captor;
    private ProductValidationServiceForEdit victim;
    private Product input = product();

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
        victim.validate(input);
        verify(productDiscountValidationRule).validate(captor.capture());
        verify(productNameValidationRule).validate(captor.capture());
        verify(productPriceValidationRule).validate(captor.capture());

        List<Product> listOfResults = captor.getAllValues();
        listOfResults.forEach(product -> assertEquals(input, product));
    }

    @Test
    public void shouldValidate2() {
        victim.validate(input);
        verify(productDiscountValidationRule).validate(captor.capture());
        verify(productNameValidationRule).validate(captor.capture());
        verify(productPriceValidationRule).validate(captor.capture());

        List<Product> listOfResults = captor.getAllValues();
        assertThat(listOfResults).containsOnly(input);
    }

    private Product product() {
        Product product = new Product();
        product.setId(0L);
        product.setDescription("TEST_DESCRIPTION");
        product.setName("TEST_NAME");
        return product;
    }
}