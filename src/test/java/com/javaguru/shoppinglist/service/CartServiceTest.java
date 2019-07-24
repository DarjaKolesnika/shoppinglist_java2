package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.database.HibernateCartRepository;
import com.javaguru.shoppinglist.domain.Cart;
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
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)

public class CartServiceTest  {
    @Mock
    private HibernateCartRepository repository;
    @Mock
    private CartValidationService carttValidationService;
    @InjectMocks
    private CartService victim;
    @Captor
    private ArgumentCaptor<Cart> cartCaptor;

    @Test
    public void shouldCreateCartSuccessfully() {
        Cart cart = cart();
        when(repository.save(cart)).thenReturn(cart.getId());
        Long result = victim.createCart(cart);
        verify(carttValidationService).validate(cartCaptor.capture());
        Cart captorResult = cartCaptor.getValue();
        assertEquals(captorResult, cart);
        assertEquals(cart.getId(), result);
    }

    @Test
    public void shouldFindCart() {
        when(repository.findCartById(1L)).thenReturn(Optional.ofNullable(cart()));
        Cart result = victim.findCartById(1L);
        assertEquals(cart(), result);
    }
    //add products, delete method tests

    @Test
    public void shouldThrowExceptionCartWasNotFound() {
        when(repository.findCartById(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> victim.findCartById(0L))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("Cart not found, id: 0");
    }

    private Cart cart() {
        Cart cart = new Cart();
        cart.setName("test");
        cart.setId(1L);
        return cart;
    }
}