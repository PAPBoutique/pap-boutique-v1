package com.pap.product.jpa.mapper;



import com.pap.product.jpa.entity.ImageDataEntity;
import com.pap.product.jpa.entity.ProductEntity;
import com.pap.product.model.ImageDataDomainObject;
import com.pap.product.model.ProductDomainObject;
import com.pap.product.rest.dto.ImageDataDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE= Mappers.getMapper(ProductMapper.class);

    ProductDomainObject productToProductDomain(ProductEntity productEntity);
    ProductEntity productDomainToProduct(ProductDomainObject productDomainObject);

    List<ProductDomainObject> productsToProductDomainObject(List<ProductEntity> productEntity);
    List<ProductEntity> productsDomainObjectToProduct(List<ProductDomainObject> productDomainObject);











}
