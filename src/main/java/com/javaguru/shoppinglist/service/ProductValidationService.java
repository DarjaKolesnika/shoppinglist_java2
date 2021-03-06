package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.dto.ProductDTO;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class ProductValidationService {
    private final Set<ProductValidation> validationRules;

    public ProductValidationService(Set<ProductValidation> validationRules) {
        this.validationRules = validationRules;
    }

    public void validate(ProductDTO productDTO) {
        validationRules.forEach(s -> s.validate(productDTO));
    }
}
