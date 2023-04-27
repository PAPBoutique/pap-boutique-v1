package com.pap.adapter;

import com.pap.adapter.jpa.entity.ProductEntity;
import com.pap.adapter.jpa.mapper.ProductMapper;
import com.pap.adapter.jpa.repository.ProductRepository;
import com.pap.model.product.ProductDomainObject;
import com.pap.ports.spi.ProductJpaPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductJpaAdapter implements ProductJpaPort {
    private final ProductRepository productRepository;

    @Override
    public List<ProductDomainObject> addProduct(List<ProductDomainObject> productDomainObject) {
        List<ProductEntity> productEntity = ProductMapper.INSTANCE.productDomainObjectToProduct(productDomainObject);
        List<ProductEntity> savedProductEntity = productRepository.saveAll(productEntity);
        return ProductMapper.INSTANCE.productToProductDomainObject(savedProductEntity);
    }

}
