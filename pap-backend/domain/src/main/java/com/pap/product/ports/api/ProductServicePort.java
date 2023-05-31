package com.pap.product.ports.api;

import com.pap.product.model.PageableContent;
import com.pap.product.model.ProductDomainObject;

import java.util.List;

public interface ProductServicePort {
    List<ProductDomainObject> addProduct(List<ProductDomainObject> productDomainObjectList);
    void deleteProduct(Long id);
    ProductDomainObject updateProduct(Long id, ProductDomainObject productDomainObjectList);
    ProductDomainObject getProductById(Long id);
    PageableContent<ProductDomainObject> findAllByPages(int page, int size,String filterValue);



}
