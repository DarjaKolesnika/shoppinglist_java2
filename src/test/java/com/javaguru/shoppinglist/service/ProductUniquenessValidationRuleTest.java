package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.database.ProductRepository;
import com.javaguru.shoppinglist.domain.Product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)

public class ProductUniquenessValidationRuleTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductUniquenessValidationRule victim;
    private Product product = product();
    @Test
    public void shouldThrowException() {
        when(productRepository.existsByName(product.getName()))
                .thenReturn(true);
        assertThatThrownBy(() -> victim.validate(product()))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Product must be unique, please choose another name");
    }
    @Test
    public void shouldValidateSuccess() {
        when(!productRepository.existsByName(product.getName()))
                .thenReturn(false);
        victim.validate(product());
    }

    private Product product() {
        Product product = new Product();
        product.setId(0L);
        product.setDescription("TEST_DESCRIPTION");
        product.setName("TEST_NAME");
        return product;
    }
}