package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.database.RepositoryInterface;
import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.dto.ProductDTO;
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
    private RepositoryInterface productRepository;
    @InjectMocks
    private ProductUniquenessValidationRule victim;
    private ProductDTO productDTO = productDTO();

    @Test
    public void shouldThrowException() {
        when(productRepository.existsByName(productDTO.getName()))
                .thenReturn(true);
        assertThatThrownBy(() -> victim.validate(productDTO()))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Product must be unique, please choose another name");
    }
    @Test
    public void shouldValidateSuccess() {
        when(!productRepository.existsByName(productDTO.getName()))
                .thenReturn(false);
        victim.validate(productDTO());
    }

    private ProductDTO productDTO() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(0L);
        productDTO.setDescription("TEST_DESCRIPTION");
        productDTO.setName("TEST_NAME");
        return productDTO;
    }
}

