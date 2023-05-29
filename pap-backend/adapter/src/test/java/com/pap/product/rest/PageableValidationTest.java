package com.pap.product.rest;

import com.pap.product.exception.InvalidPageOrSizeException;
import com.pap.product.ports.api.ProductServicePort;

import com.pap.product.rest.validator.PageableContentValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PageableValidationTest {
    @Mock
    ProductServicePort productService ;



    @BeforeEach
    public void setUp() {

    }

    @Test
    public void givenInvalidPage_whenGetProductByPage_thenShouldThrowInvalidPageOrSizeException() {
        int page = -1;
        int size = 4;
        String filterValue = "";
        assertThrows(InvalidPageOrSizeException.class,()-> PageableContentValidator.validate(page,size));
        verify(productService,times(0)).findAllByPages(page,size,filterValue);
    }

    @Test
    public void givenInvalidSize_whenGetProductByPage_thenShouldThrowInvalidPageOrSizeException() {
        int page = 0;
        int size = 7;
        String filterValue = "";
        assertThrows(InvalidPageOrSizeException.class,()-> PageableContentValidator.validate(page,size));
        verify(productService,times(0)).findAllByPages(page,size,filterValue);
    }




}