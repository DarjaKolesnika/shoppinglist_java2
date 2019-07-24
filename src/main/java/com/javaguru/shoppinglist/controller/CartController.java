package com.javaguru.shoppinglist.controller;

import com.javaguru.shoppinglist.domain.Cart;
import com.javaguru.shoppinglist.dto.CartDTO;
import com.javaguru.shoppinglist.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;

    public CartController (CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping ("/newcart")
    public ResponseEntity<Cart> create(@RequestBody CartDTO cartDTO) {
        Cart cart = new Cart();
        cart.setName(cartDTO.getName());
        cartService.createCart(cart);
        return ResponseEntity.ok(cart);
    }

    @GetMapping("/{id}")
    public CartDTO findProductById(@PathVariable("id") Long id) {
        Cart cart = cartService.findCartById(id);
        return new CartDTO(cart.getId(), cart.getName());
    }
}
