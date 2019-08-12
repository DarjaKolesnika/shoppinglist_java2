package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.converter.CartConverter;
import com.javaguru.shoppinglist.database.HibernateCartRepository;
import com.javaguru.shoppinglist.domain.Cart;
import com.javaguru.shoppinglist.dto.CartDTO;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
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
    private CartConverter cartConverter;
    @InjectMocks
    private CartService victim;
    @Captor
    private ArgumentCaptor<CartDTO> cartCaptor;

    @Test
    public void shouldCreateCartSuccessfully() {
        CartDTO cartDTO = cartDTO();
        Cart cart = cart();
        when(cartConverter.convert(cartDTO)).thenReturn(cart);
        when(repository.save(cart)).thenReturn(cart.getId());

        Long result = victim.createCart(cartDTO);
        Assertions.assertThat(cart.getId()).isEqualTo(result);
    }

    @Test
    public void shouldFindCart() {
        when(repository.findCartById(1L)).thenReturn(Optional.ofNullable(cart()));
        when(cartConverter.convert(cart())).thenReturn(cartDTO());

        CartDTO result = victim.findCartById(1L);
        Assertions.assertThat(result).isEqualTo(cartDTO());
    }

    @Test
    public void shouldDeleteCart() {
        long id = 1L;
        victim.deleteCart(id);

        when(repository.findCartById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> victim.findCartById(1L))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("Cart not found, id: 1");
    }

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

    private CartDTO cartDTO() {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setName("test");
        cartDTO.setId(1L);
        return cartDTO;
    }
}