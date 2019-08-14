package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.converter.ProductConverter;
import com.javaguru.shoppinglist.database.RepositoryInterface;
import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.dto.ProductDTO;
import org.assertj.core.api.Assertions;
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
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
@RunWith(MockitoJUnitRunner.class)

public class ProductServiceTest {

    @Mock
    private RepositoryInterface repository;
    @Mock
    private ProductValidationService productValidationService;
    @Mock
    private ProductConverter productConverter;
    @InjectMocks
    private ProductService victim;
    @Captor
    private ArgumentCaptor<ProductDTO> productCaptor;

    @Test
    public void shouldCreateProductSuccessfully() {
        ProductDTO productDTO = productDTO();
        Product product = product();
        when(productConverter.convert(productDTO)).thenReturn(product);
        when(repository.insert(product)).thenReturn(product);

        Long result = victim.createProduct(productDTO);

        verify(productValidationService).validate(productCaptor.capture());
        ProductDTO captorResult = productCaptor.getValue();

        Assertions.assertThat(captorResult).isEqualTo(productDTO);
        Assertions.assertThat(product.getId()).isEqualTo(result);
    }

    @Test
    public void shouldFindProduct() {
        when(repository.findProductById(1L)).thenReturn(Optional.ofNullable(product()));
        when(productConverter.convert(product())).thenReturn(productDTO());

        ProductDTO result = victim.findProductById(1L);
        Assertions.assertThat(result).isEqualTo(productDTO());
    }

    @Test
    public void shouldDeleteProduct() {
        long id = 1L;
        victim.deleteProduct(id);

        when(repository.findProductById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> victim.findProductById(1L))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("Product not found, id: 1");
    }

    @Test
    public void shouldThrowExceptionProductWasNotFound() {
        when(repository.findProductById(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> victim.findProductById(0L))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("Product not found, id: 0");
    }

    //edit tests

    private ProductDTO productDTO() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("test");
        productDTO.setDescription("description");
        productDTO.setPrice(new BigDecimal(30));
        productDTO.setDiscount(new BigDecimal(0));
        productDTO.setId(1L);
        return productDTO;
    }

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