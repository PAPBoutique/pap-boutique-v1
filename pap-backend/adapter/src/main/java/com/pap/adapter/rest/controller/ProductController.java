package com.pap.adapter.rest.controller;

import com.pap.adapter.dto.ProductDTO;
import com.pap.adapter.rest.mapper.ProductRestMapper;
import com.pap.adapter.rest.validator.ProductValidator;
import com.pap.product.model.ProductDomainObject;
import com.pap.product.ports.api.ProductServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductServicePort productServicePort;

    @PostMapping
    public List<ProductDomainObject> addProduct(@RequestBody List<ProductDTO> productDto) throws ValidationException {
        ProductValidator validator = new ProductValidator(productDto);
        validator.validateAllFields();
        validator.validateQuantity();
        validator.validatePrice();
        validator.validateName();
        var domainObject = ProductRestMapper.INSTANCE.convertToDomainObject(productDto);
        return productServicePort.addProduct(domainObject);
    }
}