package com.pap.adapter.rest.mapper;


import com.pap.adapter.dto.ProductDTO;
import com.pap.model.product.ProductDomainObject;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductRestMapper {
    ProductRestMapper INSTANCE = Mappers.getMapper(ProductRestMapper.class);

    List<ProductDomainObject> convertToDomainObject(List<ProductDTO> productDTO);

}

