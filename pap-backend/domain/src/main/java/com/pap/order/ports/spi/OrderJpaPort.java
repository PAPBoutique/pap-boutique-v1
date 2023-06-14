package com.pap.order.ports.spi;

import com.pap.order.model.OrderDomainObject;
import com.pap.product.model.PageableContent;

import java.util.List;

public interface OrderJpaPort {
    List<OrderDomainObject> addOrders(List<OrderDomainObject> orders);
    OrderDomainObject updateOrder(Long id,OrderDomainObject orderDomainObject);
    PageableContent<OrderDomainObject> findAllByName(int page, int size, String filter);
    PageableContent<OrderDomainObject> findAllByPage(int page, int size);
    OrderDomainObject getOrderById(Long id);
}
