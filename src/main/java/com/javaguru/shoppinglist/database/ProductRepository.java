package com.javaguru.shoppinglist.database;

import com.javaguru.shoppinglist.domain.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ProductRepository {
    private Long productIdSequence = 0L;
    private Map<Long, Product> products = new HashMap<>();

    public Product insert(Product product) {
        product.setId(productIdSequence++);
        products.put(product.getId(), product);
        return product;
    }

    public void delete(Long id) {
        products.remove(id);
    }

    public Optional<Product> findProductById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    public boolean existsByName(String name) {
        return products.values().stream()
                .anyMatch(product -> product.getName().equalsIgnoreCase(name));
    }

    public Optional<Product> findProductByName(String name) {
        return products.values().stream()
                .filter(product -> product.getName().equalsIgnoreCase(name))
                .findFirst();
    }
}
