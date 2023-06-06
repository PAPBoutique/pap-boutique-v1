package com.pap.user.jpa.mapper;

import com.pap.product.model.PageableContent;
import com.pap.user.jpa.entity.UserEntity;
import com.pap.user.model.UserDomainObject;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper
public interface UserPageableMapper {
    UserPageableMapper INSTANCE = Mappers.getMapper(UserPageableMapper.class);

    PageableContent<UserDomainObject> toUserDomainPage (Page<UserEntity> userDomainObjectPageableContent);
}
