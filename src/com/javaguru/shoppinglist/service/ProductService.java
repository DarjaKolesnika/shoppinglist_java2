package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;

import database.ProductRepository;

public class ProductService {
    private ProductRepository repository = new ProductRepository();
    private ProductValidationService validationService = new ProductValidationService();

    public Long createProduct(Product product) {
        validationService.validate(product);
        repository.insert(product);
        return product.getId();
    }

    public Product findProductById(Long id) {
        return repository.findProductById(id);
    }

    public Product editProduct(Product product) {
        validationService.validate(product);
        return product;
    }

    public void deleteProduct(Long id) {
        repository.delete(id);
    }

}
