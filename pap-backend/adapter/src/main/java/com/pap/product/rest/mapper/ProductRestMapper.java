package com.pap.product.rest.mapper;

import com.pap.product.model.ImageDataDomainObject;
import com.pap.product.model.ProductDomainObject;
import com.pap.product.rest.dto.ImageDataDTO;
import com.pap.product.rest.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductRestMapper {
    ProductRestMapper INSTANCE = Mappers.getMapper(ProductRestMapper.class);

    List<ProductDomainObject> convertToDomainObject(List<ProductDTO> productDTO);

    ProductDomainObject convertProductDtoToDomainObject(ProductDTO productDTO);

    ProductDTO convertProductDomainObjectToProductDTO(ProductDomainObject productDomainObject);


}
