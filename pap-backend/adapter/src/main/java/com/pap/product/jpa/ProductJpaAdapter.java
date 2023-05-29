package com.pap.product.jpa;

import com.pap.product.exception.ExceptionMessages;
import com.pap.product.exception.ProductNotFoundException;
import com.pap.product.jpa.entity.ProductEntity;
import com.pap.product.jpa.mapper.PageableMapper;
import com.pap.product.jpa.mapper.ProductMapper;
import com.pap.product.jpa.repository.ProductRepository;
import com.pap.product.model.PageableContent;
import com.pap.product.model.ProductDomainObject;
import com.pap.product.ports.spi.ProductJpaPort;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;


@AllArgsConstructor
@Component
public class ProductJpaAdapter implements ProductJpaPort {
    private final ProductRepository productRepository;

    @Override
    public List<ProductDomainObject> addProduct(List<ProductDomainObject> productDomainObject) {
        List<ProductEntity> productEntity = ProductMapper.INSTANCE.productsDomainObjectToProduct(productDomainObject);
        List<ProductEntity> savedProductEntity = productRepository.saveAll(productEntity);
        return ProductMapper.INSTANCE.productsToProductDomainObject(savedProductEntity);
    }

    @Override
    public void deleteProduct(Long id) {
        if(getProductById(id)!=null){
            productRepository.deleteById(id);
        }

    }

    @Override
    public PageableContent<ProductDomainObject> findAllByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductEntity> productPage = productRepository.findAll(pageable);
        return PageableMapper.INSTANCE.toPageableContent(productPage);
    }

    @Override
    public PageableContent<ProductDomainObject> findAllByName(int page, int size, String name) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductEntity> productPage = productRepository.findAllByNameContainingIgnoreCase(name,pageable);
        return PageableMapper.INSTANCE.toPageableContent(productPage);
    }

    public ProductEntity getProductById(Long id){
        return productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException(ExceptionMessages.PRODUCTNOTFOUND));
    }
}
