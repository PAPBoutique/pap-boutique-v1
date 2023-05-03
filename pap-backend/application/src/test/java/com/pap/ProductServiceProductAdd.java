package com.pap;

import com.pap.product.model.ProductDomainObject;
import com.pap.product.ports.spi.ProductJpaPort;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@ExtendWith(value = MockitoExtension.class)
public class ProductServiceProductAdd {
    @InjectMocks
   private ProductService productService;
    @Mock
    private ProductJpaPort productJpaPortMock;
    private List<ProductDomainObject> productsToAdd ;
    @BeforeEach
    public void setUp() {
        productsToAdd = new ArrayList<>() ;
    }
    @Test
    public void Given_AProduct_WhenAddingValidProduct_ThenReturnListOfAddedProductsWithCorrectValues() {
        productsToAdd.add(ProductDomainObject.builder()
                .name("Add product test")
                .description("Test test")
                .quantity(10)
                .price(20.0)
                .build());

        when(productJpaPortMock.addProduct(anyList())).thenReturn(productsToAdd);

        List<ProductDomainObject> addedProducts = productService.addProduct(productsToAdd);

        assertNotNull("List of added products should not be null", addedProducts);
        assertEquals(1, addedProducts.size());

        ProductDomainObject addedProduct = addedProducts.get(0);
        assertEquals("Add product test", addedProduct.getName());
        assertEquals("Test test", addedProduct.getDescription());
        assertEquals(10, addedProduct.getQuantity());
        assertEquals(20.0, addedProduct.getPrice(), 0.0);
        verify(productJpaPortMock, times(1)).addProduct(addedProducts);
        verifyNoMoreInteractions(productJpaPortMock);
    }

}