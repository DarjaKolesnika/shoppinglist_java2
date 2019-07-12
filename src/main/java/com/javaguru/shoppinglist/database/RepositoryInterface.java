package com.javaguru.shoppinglist.database;

import com.javaguru.shoppinglist.domain.Product;
import java.util.Optional;

public interface RepositoryInterface {
    Product insert(Product task);

    Optional<Product> findProductById(Long id);

    boolean existsByName(String name);

    void delete(Product product);

    void saveEditedProduct(Long id, Product product);
}
