package com.pap.product.service;

import com.pap.product.model.PageableContent;
import com.pap.product.model.ProductDomainObject;
import com.pap.product.ports.api.ProductServicePort;
import com.pap.product.ports.spi.ProductJpaPort;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductService implements ProductServicePort {
    private final ProductJpaPort productJpaPort ;

    @Override
    public List<ProductDomainObject> addProduct(List<ProductDomainObject> product) {
        return productJpaPort.addProduct(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productJpaPort.deleteProduct(id);
    }

    @Override
    public ProductDomainObject updateProduct(Long id, ProductDomainObject productDomainObjectList) {
        return productJpaPort.updateProduct(id, productDomainObjectList);
    }

    @Override
    public ProductDomainObject getProductById(Long id) {
        return productJpaPort.getProductById(id);
    }

    @Override
    public PageableContent<ProductDomainObject> findAllByPages(int page, int size, String filterValue) {
        if(filterValue.isBlank()){
            return productJpaPort.findAllByPage(page,size);
        }else {
            PageableContent<ProductDomainObject> result = productJpaPort.findAllByName(page,size,filterValue.toLowerCase());
            if(result.getContent()==null) result.setContent(new ArrayList<>());
            return result;
        }

    }





}
