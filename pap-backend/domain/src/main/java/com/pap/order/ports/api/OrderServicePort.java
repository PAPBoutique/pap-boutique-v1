package com.pap.order.ports.api;

import com.pap.order.model.OrderDomainObject;
import com.pap.product.model.PageableContent;

import java.util.List;

public interface OrderServicePort {

    List<OrderDomainObject> addOrders(List<OrderDomainObject> orders);
    OrderDomainObject updateOrder(Long id,OrderDomainObject orderDomainObject);
    PageableContent<OrderDomainObject> getOrders(int page,int size,String filter);
    Double priceCalculation(OrderDomainObject orderDomainObject);
    void checkOrder(Long id);

}
