package com.pap.order.service;

import com.pap.order.model.OrderDomainObject;
import com.pap.order.ports.api.OrderServicePort;
import com.pap.order.ports.spi.OrderJpaPort;
import com.pap.product.exception.ProductNotFoundException;
import com.pap.product.model.PageableContent;
import com.pap.product.model.ProductDomainObject;
import com.pap.product.ports.spi.ProductJpaPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements OrderServicePort {
    private final OrderJpaPort orderJpaPort ;
    private final ProductJpaPort productJpaPort ;

    @Override
    public List<OrderDomainObject> addOrders(List<OrderDomainObject> orders) {
        orders.forEach(orderDomainObject -> {
            if(productJpaPort.getProductById(orderDomainObject.getProductId())== null){
                throw new ProductNotFoundException("This product does not exist");
            }
            orderDomainObject.setPrice(priceCalculation(orderDomainObject));
        });
        return orderJpaPort.addOrders(orders);
    }

    @Override
    public OrderDomainObject updateOrder(Long id, OrderDomainObject orderDomainObject) {
        if(productJpaPort.getProductById(orderDomainObject.getProductId())== null)
            throw new ProductNotFoundException("This product does not exist");
        return orderJpaPort.updateOrder(id,orderDomainObject);
    }

    @Override
    public PageableContent<OrderDomainObject> getOrders(int page, int size, String filter) {
        if(filter.isBlank()){
            return orderJpaPort.findAllByPage(page, size);
        }
        return orderJpaPort.findAllByName(page, size, filter);
    }

    @Override
    public Double priceCalculation(OrderDomainObject orderDomainObject) {
        ProductDomainObject product = productJpaPort.getProductById(orderDomainObject.getProductId());
        return product.getPrice() * orderDomainObject.getQuantity();
    }
}
