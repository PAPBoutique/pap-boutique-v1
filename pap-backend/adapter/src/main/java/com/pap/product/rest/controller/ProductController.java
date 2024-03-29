package com.pap.product.rest.controller;


import com.pap.product.exception.ProductNotFoundException;
import com.pap.product.model.PageableContent;
import com.pap.product.model.ProductDomainObject;
import com.pap.product.ports.api.ProductServicePort;
import com.pap.product.rest.constants.ProductDocMessages;
import com.pap.product.rest.dto.ProductDTO;
import com.pap.product.rest.mapper.ProductRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Validated
public class ProductController {
    private final ProductServicePort productService ;
    @Operation(summary = ProductDocMessages.ADD_PRODUCTS)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = ProductDocMessages.ADD_PRODUCTS_SUCCESS)
    })
    @PostMapping
    public List<ProductDomainObject> addProduct(
            @RequestBody @Valid
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = ProductDocMessages.ADD_PRODUCTS_PARAM) List<@Valid ProductDTO> productDto){
        List<ProductDomainObject> domainObject = ProductRestMapper.INSTANCE.convertToDomainObject(productDto);
        return productService.addProduct(domainObject);
    }

    @Operation(summary = ProductDocMessages.GET_PRODUCTS)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" , description = ProductDocMessages.GET_PRODUCTS_SUCCESS , content = @Content),
            @ApiResponse(responseCode = "400" , description = ProductDocMessages.GET_PRODUCTS_FAILED)
    })
    @GetMapping
    public PageableContent<ProductDomainObject> getProductsByPage(
            @RequestParam @Min(value = 0 , message = "Page number must be greater than or equal 0") int page,
            @RequestParam @Max(value= 6 , message = "Size must be lower than 6") int size,
            @RequestParam String filterValue){
        return productService.findAllByPages(page,size,filterValue);
    }

    @GetMapping("/{id}")
    @Operation(summary = ProductDocMessages.GET_PRODUCT_BY_ID)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" , description = ProductDocMessages.GET_PRODUCTS_SUCCESS , content = @Content),
            @ApiResponse(responseCode = "400" , description = ProductDocMessages.GET_PRODUCTS_FAILED)
    })
    public ProductDTO getProductById(@PathVariable("id") Long id) {
        try {
            var domainObject = productService.getProductById(id);
            return ProductRestMapper.INSTANCE.convertProductDomainObjectToProductDTO(domainObject);
        } catch (ProductNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @PutMapping("/update/{id}")
    @Operation(summary = ProductDocMessages.UPDATE_PRODUCT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" , description = ProductDocMessages.UPDATE_PRODUCT_SUCCESS)
    })
    public ProductDomainObject updateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO productDto) {
        try {
            var domainObject = ProductRestMapper.INSTANCE.convertProductDtoToDomainObject(productDto);
            return productService.updateProduct(id, domainObject);
        } catch (ProductNotFoundException ex) {
            throw new ProductNotFoundException(ex.getMessage());
        }
    }

    @Operation(summary = ProductDocMessages.DELETE_PRODUCTS)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" , description = ProductDocMessages.DELETE_PRODUCTS_SUCCESS)
    })
    @DeleteMapping("/{id}")
    public void deleteProduct(
            @PathVariable
            @Parameter(name = ProductDocMessages.DELETE_PRODUCTS_PARAM) Long id){
        productService.deleteProduct(id);
    }






}
