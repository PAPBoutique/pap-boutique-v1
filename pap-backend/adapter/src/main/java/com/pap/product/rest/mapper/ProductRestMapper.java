package com.pap.product.rest.mapper;

import com.pap.product.model.ProductDomainObject;
import com.pap.product.rest.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductRestMapper {
    ProductRestMapper INSTANCE = Mappers.getMapper(ProductRestMapper.class);


    ProductDomainObject toProductDomain(ProductDTO productDTO);


    List<ProductDomainObject> convertToDomainObject(List<ProductDTO> productDTO);


}
