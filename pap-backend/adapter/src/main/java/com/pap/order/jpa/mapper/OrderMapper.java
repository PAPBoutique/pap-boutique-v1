package com.pap.order.jpa.mapper;

import com.pap.order.jpa.entity.OrderEntity;
import com.pap.order.model.OrderDomainObject;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderEntity toOrderEntity(OrderDomainObject orderDomainObject);
    OrderDomainObject toOrderDomainObject(OrderEntity orderEntity);
    List<OrderEntity> toOrderEntityList(List<OrderDomainObject> orderDomainObjectList);
    List<OrderDomainObject> toOrderDomainObjectList(List<OrderEntity> orderEntityList);
}
