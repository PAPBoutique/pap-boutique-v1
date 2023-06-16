package com.pap.product.rest.mapper;

import com.pap.product.model.ImageDataDomainObject;
import com.pap.product.rest.dto.ImageDataDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface ImageRestMapper {
    ImageRestMapper INSTANCE = Mappers.getMapper(ImageRestMapper.class);

    ImageDataDTO imageDomainObjectToImageDto(ImageDataDomainObject imageDataDomainObject);
    Set<ImageDataDTO> setImageDataDtoToImageDataDomain(Set<ImageDataDomainObject> imageDataDomain);

    ImageDataDomainObject convertImageDtoToDomainObject(ImageDataDTO imageDto);


}
