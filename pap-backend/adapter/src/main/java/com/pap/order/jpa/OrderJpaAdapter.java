package com.pap.order.jpa;

import com.pap.order.jpa.entity.OrderEntity;
import com.pap.order.jpa.exception.OrderNotFoundException;
import com.pap.order.jpa.mapper.OrderMapper;
import com.pap.order.jpa.mapper.OrderPageableMapper;
import com.pap.order.jpa.repository.OrderRepository;
import com.pap.order.model.OrderDomainObject;
import com.pap.order.ports.spi.OrderJpaPort;
import com.pap.product.jpa.mapper.ProductMapper;
import com.pap.product.model.PageableContent;
import com.pap.product.model.ProductDomainObject;
import com.pap.user.jpa.exception.UserNotFoundException;
import com.pap.user.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderJpaAdapter implements OrderJpaPort {
    private final OrderRepository orderRepository ;
    private final UserRepository userRepository;

    @Override
    public List<OrderDomainObject> addOrders(List<OrderDomainObject> orders) {
        orders.forEach(orderDomainObject -> {
            if(userRepository.findByUsername(orderDomainObject.getClient()).isEmpty()) throw new UserNotFoundException("This user does not exist");
            //If one order fails, others will fail too !!
        });
        List<OrderEntity> orderEntities = OrderMapper.INSTANCE.toOrderEntityList(orders);
        List<OrderEntity> ordersToAdd = orderRepository.saveAll(orderEntities);
        return OrderMapper.INSTANCE.toOrderDomainObjectList(ordersToAdd);
    }

    @Override
    public OrderDomainObject updateOrder(Long id, OrderDomainObject orderDomainObject) {
        OrderEntity updatedOrder = orderRepository.findById(id).orElseThrow(()->new OrderNotFoundException("Order not found"));
        updatedOrder.setId(orderDomainObject.getId());
        updatedOrder.setProductId(orderDomainObject.getProductId());
        updatedOrder.setPrice(orderDomainObject.getPrice());
        updatedOrder.setCreationDate(orderDomainObject.getCreationDate());
        updatedOrder.setClient(orderDomainObject.getClient());
        updatedOrder.setQuantity(orderDomainObject.getQuantity());
        orderRepository.save(updatedOrder);
        return OrderMapper.INSTANCE.toOrderDomainObject(updatedOrder);
    }

    @Override
    public PageableContent<OrderDomainObject> findAllByName(int page, int size, String filter) {
        Pageable pageable = PageRequest.of(page, size);
        Page<OrderEntity> entityPage = orderRepository.findAllByClientContainingIgnoreCase(filter,pageable);
        return OrderPageableMapper.INSTANCE.toOrderDomainPage(entityPage);
    }

    @Override
    public PageableContent<OrderDomainObject> findAllByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<OrderEntity> entityPage = orderRepository.findAll(pageable);
        return OrderPageableMapper.INSTANCE.toOrderDomainPage(entityPage);
    }

    @Override
    public OrderDomainObject getOrderById(Long id) {
        OrderEntity order =  orderRepository.findById(id).orElseThrow(()->new OrderNotFoundException("Order not found"));
        return OrderMapper.INSTANCE.toOrderDomainObject(order);
    }

    @Override
    public void checkOrder(Long id) {
        OrderEntity order = orderRepository.findById(id).orElseThrow(()->new OrderNotFoundException("Order not found"));
        order.setChecked(true);
        orderRepository.save(order);
    }


}
