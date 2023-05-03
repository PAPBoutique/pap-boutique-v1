package com.pap.product.ports.spi;

import com.pap.product.model.ProductDomainObject;

import java.util.List;

public interface ProductJpaPort {
    List<ProductDomainObject> addProduct(List<ProductDomainObject> productDomainObjectList);

}
