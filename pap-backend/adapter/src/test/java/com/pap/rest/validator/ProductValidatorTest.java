package com.pap.rest.validator;

import com.pap.adapter.dto.ProductDTO;
import com.pap.adapter.rest.validator.ProductValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.bind.ValidationException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductValidatorTest {
    List<ProductDTO> testDataProduct;

    @BeforeEach
    public void setUp() {
        testDataProduct = new ArrayList<>() ;
    }

    @Test()
    public void Given_AProductWithNullValues_WhenSaving_ThenShouldThrowValidationException() throws ValidationException {
        testDataProduct.add(ProductDTO.builder()
                .name(null)
                .description(null)
                .price(null)
                .quantity(null)
                .build());
        ProductValidator validator = new ProductValidator(testDataProduct);
        assertThrows(ValidationException.class, validator::validateAllFields);
    }

    @Test()
    public void Given_AProductWithNullQuantity_WhenSaving_ThenShouldThrowValidationException() throws ValidationException {
        testDataProduct.add(ProductDTO.builder()
                .name("Test")
                .description("Test")
                .price(10.0)
                .quantity(null)
                .build());
        ProductValidator validator = new ProductValidator(testDataProduct);
        assertThrows(ValidationException.class, validator::validateQuantity);
    }

    @Test()
    public void Given_AProductWithNullPrice_WhenSaving_ThenShouldThrowValidationException() throws ValidationException {
        testDataProduct.add(ProductDTO.builder()
                .name("Test")
                .description("Test")
                .price(null)
                .quantity(12)
                .build());
        ProductValidator validator = new ProductValidator(testDataProduct);
        assertThrows(ValidationException.class, validator::validatePrice);
    }

    @Test()
    public void Given_AProductWithNullOrEmptyName_WhenSaving_ThenShouldThrowValidationException() throws ValidationException {
        testDataProduct.add(ProductDTO.builder()
                .name("")
                .description("null")
                .price(10.0)
                .quantity(12)
                .build());
        ProductValidator validator = new ProductValidator(testDataProduct);
        assertThrows(ValidationException.class, validator::validateName);
    }
    
}
