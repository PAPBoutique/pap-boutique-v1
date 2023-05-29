package com.pap.product.jpa.mapper;

import com.pap.product.jpa.entity.ProductEntity;
import com.pap.product.model.PageableContent;
import com.pap.product.model.ProductDomainObject;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;


@Mapper
public interface PageableMapper {
    PageableMapper INSTANCE = Mappers.getMapper(PageableMapper.class);

    PageableContent<ProductDomainObject> toPageableContent(Page<ProductEntity> page);


}
