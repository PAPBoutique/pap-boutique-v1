package com.pap.adapter.rest.controller;

import com.pap.adapter.dto.ProductDTO;
import com.pap.adapter.rest.mapper.ProductRestMapper;
import com.pap.model.product.ProductDomainObject;
import com.pap.ports.api.ProductServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServicePort productServicePort;

    @PostMapping
    // todo List<ProductDTO>
    public List<ProductDomainObject> addProduct(@RequestBody List<ProductDTO> productDto) {
        var domainObject = ProductRestMapper.INSTANCE.convertToDomainObject(productDto);
        return productServicePort.addProduct(domainObject);
    }
    @GetMapping
    public List<ProductDomainObject> getAllProducts() {
        return productServicePort.getProducts();
    }
}