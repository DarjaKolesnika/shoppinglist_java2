package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.database.RepositoryInterface;
import com.javaguru.shoppinglist.domain.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.NoSuchElementException;

@Service
public class ProductService {
    private final RepositoryInterface repository;
    private final ProductValidationService validationService;
    private final ProductValidationServiceForEdit validationServiceForEdit;

    public ProductService(RepositoryInterface productRepository,
            ProductValidationService validationService, @Qualifier("validation for edit") ProductValidationServiceForEdit
               validationServiceForEdit) {
        this.repository = productRepository;
        this.validationService = validationService;
        this.validationServiceForEdit = validationServiceForEdit;
    }

    @Transactional
    public Long createProduct(Product product) {
        validationService.validate(product);
        Product createdProduct = repository.insert(product);
        return createdProduct.getId();
    }

    public Product findProductById(Long id) {
        return repository.findProductById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found, id: " + id));
    }

    @Transactional
    public Product editProduct(Product product, String userOption, String newFieldInfo) {
        Product copyOfProduct = new Product(product);
        switch (userOption) {
            case "1":
                copyOfProduct.setName(newFieldInfo);
                validationService.validate(copyOfProduct);
                repository.saveEditedProduct(product.getId(), copyOfProduct);
                break;
            case "2":
                copyOfProduct.setPrice(new BigDecimal(newFieldInfo).setScale(2, RoundingMode.CEILING));
                validationServiceForEdit.validate(copyOfProduct);
                repository.saveEditedProduct(product.getId(), copyOfProduct);
                break;
            case "3":
                copyOfProduct.setDiscount(new BigDecimal(newFieldInfo).setScale(1, RoundingMode.CEILING));
                validationServiceForEdit.validate(copyOfProduct);
                repository.saveEditedProduct(product.getId(), copyOfProduct);
                break;
            case "4":
                copyOfProduct.setDescription(newFieldInfo);
                validationServiceForEdit.validate(copyOfProduct);
                repository.saveEditedProduct(product.getId(), copyOfProduct);
                break;
        }
        return copyOfProduct;
    }

    public void deleteProduct(Product product) {
        repository.delete(product);
    }
}