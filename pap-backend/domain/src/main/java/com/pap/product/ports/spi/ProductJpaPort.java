package com.pap.product.ports.spi;

import com.pap.product.model.PageableContent;
import com.pap.product.model.ProductDomainObject;

import java.util.List;


public interface ProductJpaPort {
    List<ProductDomainObject> addProduct(List<ProductDomainObject> productDomainObjectList);
    ProductDomainObject updateProduct(Long id, ProductDomainObject productDomainObjectList);
    ProductDomainObject getProductById(Long id);

    void deleteProduct(Long id);
    PageableContent<ProductDomainObject> findAllByPage(int page, int size);
    PageableContent<ProductDomainObject> findAllByName(int page,int size,String name);
}
