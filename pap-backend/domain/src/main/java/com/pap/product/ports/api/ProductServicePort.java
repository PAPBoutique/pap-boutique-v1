package com.pap.product.ports.api;

import com.pap.product.model.ProductDomainObject;



import java.util.List;

public interface ProductServicePort {
    List<ProductDomainObject> addProduct(List<ProductDomainObject> productDomainObjectList);
}
