package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.database.RepositoryInterface;
import com.javaguru.shoppinglist.domain.Product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)

public class ProductServiceTest {

    @Mock
    private RepositoryInterface repository;
    @Mock
    private ProductValidationService productValidationService;
    @InjectMocks
    private ProductService victim;
    @Captor
    private ArgumentCaptor<Product> productCaptor;

    @Test
    public void shouldCreateProductSuccessfully() {
        Product product = product();
        when(repository.insert(product)).thenReturn(product);
        Long result = victim.createProduct(product);
        verify(productValidationService).validate(productCaptor.capture());
        Product captorResult = productCaptor.getValue();
        assertEquals(captorResult, product);
        assertEquals(product.getId(), result);
    }

    @Test
    public void shouldFindProduct() {
        when(repository.findProductById(1L)).thenReturn(Optional.ofNullable(product()));
        Product result = victim.findProductById(1L);
        assertEquals(product(), result);
    }

    @Test
    public void shouldDeleteProduct() {
        long productId = 1L;
        victim.deleteProduct(victim.findProductById(productId));
        verify(repository, times(1)).delete(victim.findProductById(productId));
    }

    @Test
    public void shouldThrowExceptionProductWasNotFound() {
        when(repository.findProductById(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> victim.findProductById(0L))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("Product not found, id: 0");
    }

    //edit tests

    private Product product() {
        Product product = new Product();
        product.setName("test");
        product.setDescription("description");
        product.setPrice(new BigDecimal(30));
        product.setDiscount(new BigDecimal(0));
        product.setId(1L);
        return product;
    }
}