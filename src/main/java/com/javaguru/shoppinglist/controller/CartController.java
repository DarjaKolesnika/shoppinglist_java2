package com.javaguru.shoppinglist.controller;

import com.javaguru.shoppinglist.dto.CartDTO;
import com.javaguru.shoppinglist.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/version1/carts")
public class CartController {
    private CartService cartService;

    public CartController (CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<Void> create(@Validated({CartDTO.Create.class}) @RequestBody CartDTO cartDTO,
                                       UriComponentsBuilder builder) {
        Long id = cartService.createCart(cartDTO);
        return ResponseEntity.created(builder.path("/carts/{id}").buildAndExpand(id).toUri()).build();
    }

    @GetMapping("/{id}")
    public CartDTO findCartById(@PathVariable("id") Long id) {
        return cartService.findCartById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        cartService.deleteCart(id);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNoSuchElementException(NoSuchElementException e) {
    }
}
