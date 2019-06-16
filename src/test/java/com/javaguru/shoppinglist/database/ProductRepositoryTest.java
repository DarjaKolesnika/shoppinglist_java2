package com.javaguru.shoppinglist.database;

import com.javaguru.shoppinglist.domain.Product;

import static org.junit.Assert.*;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)

public class ProductRepositoryTest {
    private static final String PRODUCT_NAME = "TEST_NAME";
    private static final String PRODUCT_DESCRIPTION = "TEST_DESCRIPTION";
    private static final long PRODUCT_ID = 0L;

    @Spy
    private ProductRepository victim = new ProductRepository();
    private Product product = expectedProduct();

    @Test
    public void shouldInsert() {
        Product result = victim.insert(product);

        assertThat(result).isEqualTo(expectedProduct());
    }

    @Test
    public void shouldFindById() {
        victim.insert(product);

        Optional<Product> result = victim.findProductById(PRODUCT_ID);
        assertThat(result).isNotEmpty();
        assertThat(result).hasValue(expectedProduct());
    }

    @Test
    public void shouldFindByName() {
        victim.insert(product);

        Optional<Product> result = victim.findProductByName(PRODUCT_NAME);
        assertThat(result).isNotEmpty();
        assertThat(result).hasValue(expectedProduct());
    }

    @Test
    public void shouldExistByName() {
        victim.insert(product);

        boolean result = victim.existsByName(PRODUCT_NAME);
        assertThat(result).isTrue();
    }

   @Test
    public void shouldDelete(){
       victim.insert(product);
       victim.delete(product.getId());
       Optional<Product> result = victim.findProductById(PRODUCT_ID);
       assertThat(result).isEmpty();
   }

    private Product expectedProduct() {
        Product product = new Product();
        product.setName("TEST_NAME");
        product.setDescription("TEST_DESCRIPTION");
        product.setId(0L);
        return product;
    }
}