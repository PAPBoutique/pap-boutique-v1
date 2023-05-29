package com.pap.product.service;



import com.pap.product.model.PageableContent;
import com.pap.product.model.ProductDomainObject;
import com.pap.product.ports.spi.ProductJpaPort;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceListProducts {

    @Mock
    private ProductJpaPort productJpaPortMock ;
    @InjectMocks
    private ProductService productService;

    private List<ProductDomainObject> products ;



    @BeforeEach
    public void setUp() {
        products = new ArrayList<>() ;
    }

    @Test
    public void givenBlankFilterValue_whenFindAllByPages_thenReturnsAllRecords() {
        int page = 0;
        int size = 4;
        String filterValue = "";
        products.add(ProductDomainObject.builder()
                .id(1L)
                .name("Product 1")
                .description("description 1 ")
                .quantity(10)
                .price(50.0)
                .createdDate(LocalDate.now()).build());
        products.add(ProductDomainObject.builder()
                .id(1L)
                .name("Product 2")
                .description("description 2 ")
                .quantity(20)
                .price(100.0)
                .createdDate(LocalDate.now()).build());
        when(productJpaPortMock.findAllByPage(page, size)).thenReturn(new PageableContent<>(products, 1, 2));
        PageableContent<ProductDomainObject> result = productService.findAllByPages(page, size, filterValue);

        assertNotNull(result);
        assertEquals(1, result.getTotalPages());
        assertEquals(2, result.getTotalElements());
        assertEquals(products, result.getContent());
        verify(productJpaPortMock, times(1)).findAllByPage(page, size);
        verifyNoMoreInteractions(productJpaPortMock);
    }

    @Test
    public void givenNonBlankFilterValue_whenFindAllByPages_thenReturnsMatchingRecords() {
        int page = 0;
        int size = 2;
        String filterValue = "test";
        products.add(ProductDomainObject.builder()
                .id(1L)
                .name("Product 1")
                .description("description 1 ")
                .quantity(10)
                .price(50.0)
                .createdDate(LocalDate.now()).build());
        products.add(ProductDomainObject.builder()
                .id(1L)
                .name("Product 2")
                .description("description 2 ")
                .quantity(20)
                .price(100.0)
                .createdDate(LocalDate.now()).build());
        when(productJpaPortMock.findAllByName(page, size, filterValue.toLowerCase()))
                .thenReturn(new PageableContent<>(null, 0, 0));


        PageableContent<ProductDomainObject> result = productService.findAllByPages(page, size, filterValue);

        assertNotNull(result);
        assertEquals(0, result.getTotalPages());
        assertEquals(0, result.getTotalElements());
        assertNull(result.getContent());
    }


}

