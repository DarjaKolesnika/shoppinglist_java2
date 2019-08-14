package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.dto.ProductDTO;
import java.util.Set;

public class ProductValidationServiceForEdit {
    private Set<ProductValidation> validationRules;

    public ProductValidationServiceForEdit(Set<ProductValidation> validationRules) {
        this.validationRules = validationRules;
    }

    public void validate(ProductDTO productDTO) {
        validationRules.forEach(s -> s.validate(productDTO));
    }
}
