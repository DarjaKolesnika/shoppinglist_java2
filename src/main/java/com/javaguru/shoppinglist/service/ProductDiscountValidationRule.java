package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductDiscountValidationRule implements ProductValidation{
    @Override
    public void validate(Product product) {
        BigDecimal biggestDiscount = new BigDecimal(80);
        BigDecimal minPriceForDiscount = new BigDecimal(20);

        checkNotNull(product);

        if (product.getDiscount() == null) {
            throw new ValidationException("Product discount must be not null.");
        }
        if (product.getDiscount().compareTo(biggestDiscount) == 1) {
            throw new ValidationException("Product discount must not exceed 80%");
        }
        if (product.getPrice().compareTo(minPriceForDiscount) == -1 && product.getDiscount().compareTo(BigDecimal.ZERO) > 0) {
            throw new ValidationException("Products with price under 20 EUR cannot have a discount");
        }
    }
}
