package com.pap.user.rest.mapper;

import com.pap.user.model.UserDomainObject;
import com.pap.user.rest.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserRestMapper {
    UserRestMapper INSTANCE = Mappers.getMapper(UserRestMapper.class);

    List<UserDomainObject> convertToDomainObject(List<UserDto> userDTOs);

    UserDomainObject converUserDtoToDomainObject(UserDto userDto);


}
