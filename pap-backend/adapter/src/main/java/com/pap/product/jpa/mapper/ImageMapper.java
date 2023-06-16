package com.pap.product.jpa.mapper;

import com.pap.product.jpa.entity.ImageDataEntity;
import com.pap.product.model.ImageDataDomainObject;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface ImageMapper {
    ImageMapper INSTANCE= Mappers.getMapper(ImageMapper.class);

    Set<ImageDataDomainObject> imageDataEntityToImageDataDomain(Set<ImageDataEntity> imageEntity);

    Set<ImageDataEntity> convertImageDataDomainSetToEntitySet(Set<ImageDataDomainObject> imageDataDomainObjects);

    ImageDataEntity convertImageDataDomainToEntity(ImageDataDomainObject domainObjects);

    ImageDataDomainObject imageDataToImageDataDomain(ImageDataEntity imageDataEntity);
}
