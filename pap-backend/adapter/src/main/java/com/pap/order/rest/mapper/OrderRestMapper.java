package com.pap.order.rest.mapper;


import com.pap.order.model.OrderDomainObject;
import com.pap.order.rest.dto.OrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderRestMapper {
    OrderRestMapper INSTANCE = Mappers.getMapper(OrderRestMapper.class);

    OrderDTO toOrderDto(OrderDomainObject orderDomainObject);
    OrderDomainObject toOrderDomain (OrderDTO orderDTO);
    List<OrderDomainObject> toOrderDomainList (List<OrderDTO> orderDTOList);
}
