package com.pap.order.jpa.mapper;

import com.pap.order.jpa.entity.OrderEntity;
import com.pap.order.model.OrderDomainObject;
import com.pap.product.model.PageableContent;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper
public interface OrderPageableMapper {
    OrderPageableMapper INSTANCE = Mappers.getMapper(OrderPageableMapper.class);

    PageableContent<OrderDomainObject> toOrderDomainPage(Page<OrderEntity> orderEntityPage);
}
