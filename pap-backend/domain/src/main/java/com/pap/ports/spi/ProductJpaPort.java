package com.pap.ports.spi;

import com.pap.model.product.ProductDomainObject;

import java.util.List;

public interface ProductJpaPort {
    List<ProductDomainObject> addProduct(List<ProductDomainObject> productDomainObjectList);
    List<ProductDomainObject> getProducts();

}
