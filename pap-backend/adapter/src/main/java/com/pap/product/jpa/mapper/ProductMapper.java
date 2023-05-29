package com.pap.product.jpa.mapper;


import com.pap.product.jpa.entity.ProductEntity;
import com.pap.product.model.ProductDomainObject;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE= Mappers.getMapper(ProductMapper.class);


    ProductDomainObject productToProductDomain(ProductEntity productEntity);


    ProductEntity productDomainToProduct(ProductDomainObject productDomainObject);


    List<ProductDomainObject> productsToProductDomainObject(List<ProductEntity> productEntity);

    List<ProductEntity> productsDomainObjectToProduct(List<ProductDomainObject> productDomainObject);

    List<ProductDomainObject> productsListToProductDomainObject(List<ProductEntity> productEntityList);


}
