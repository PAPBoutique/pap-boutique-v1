package com.pap.adapter.jpa.mapper;

import com.pap.adapter.jpa.entity.ProductEntity;
import com.pap.product.model.ProductDomainObject;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    List<ProductDomainObject> productToProductDomainObject(List<ProductEntity> productEntity);

    List<ProductEntity> productDomainObjectToProduct(List<ProductDomainObject> productDomainObject);

    List<ProductDomainObject> productListToProductDomainObject(List<ProductEntity> productEntityList);

}