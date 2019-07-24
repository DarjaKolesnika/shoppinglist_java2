package com.javaguru.shoppinglist.controller;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/newproduct")
    public ResponseEntity<Product> create(@RequestBody ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDiscount(productDTO.getDiscount());
        product.setDescription(productDTO.getDescription());
        product.setCategory(productDTO.getCategory());
        productService.createProduct(product);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/{id}")
    public ProductDTO findProductById(@PathVariable("id") Long id) {
        Product product = productService.findProductById(id);
        return new ProductDTO(product.getId(), product.getName(), product.getPrice(), product.getDiscount(),
                product.getDescription(), product.getCategory(), product.getCartId());
    }
}
