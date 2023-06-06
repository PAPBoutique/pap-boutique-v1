package com.pap.user.jpa.mapper;

import com.pap.user.jpa.entity.UserEntity;
import com.pap.user.model.UserDomainObject;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDomainObject toUserDomain (UserEntity userEntity);
    UserEntity toUserEntity(UserDomainObject userDomainObject);
    List<UserDomainObject> toListUserDomain(List<UserEntity> userEntityList);
}
