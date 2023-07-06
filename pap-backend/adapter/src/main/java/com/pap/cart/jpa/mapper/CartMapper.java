package com.pap.cart.jpa.mapper;

import com.pap.cart.jpa.entity.CartEntity;
import com.pap.cart.model.CartDomainObject;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CartMapper {

    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);
    CartDomainObject toCartDomain (CartEntity cartEntity);
    CartEntity toCartEntity(CartDomainObject cartDomainObject);
    List<CartDomainObject> toCartDomainList(List<CartEntity> cartEntity);
    List<CartEntity> toCartEntityList(List<CartDomainObject> cartDomainObject);

}
