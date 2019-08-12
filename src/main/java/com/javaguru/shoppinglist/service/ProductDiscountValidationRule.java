package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.dto.ProductDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductDiscountValidationRule implements ProductValidation{
    @Override
    public void validate(ProductDTO productDTO) {
        BigDecimal biggestDiscount = new BigDecimal(80);
        BigDecimal minPriceForDiscount = new BigDecimal(20);

        checkNotNull(productDTO);

        if (productDTO.getDiscount() == null) {
            throw new ValidationException("Product discount must be not null.");
        }
        if (productDTO.getDiscount().compareTo(biggestDiscount) == 1) {
            throw new ValidationException("Product discount must not exceed 80%");
        }
        if (productDTO.getPrice().compareTo(minPriceForDiscount) == -1 && productDTO.getDiscount().compareTo(BigDecimal.ZERO) > 0) {
            throw new ValidationException("Products with price under 20 EUR cannot have a discount");
        }
    }
}
