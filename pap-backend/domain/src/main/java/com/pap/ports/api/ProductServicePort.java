package com.pap.ports.api;

import com.pap.model.product.ProductDomainObject;

import java.util.List;

public interface ProductServicePort {
    List<ProductDomainObject> addProduct(List<ProductDomainObject> productDomainObjectList);
    List<ProductDomainObject> getProducts();


}
