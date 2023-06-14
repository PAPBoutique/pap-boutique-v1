package com.pap.order.rest.controller;

import com.pap.order.model.OrderDomainObject;
import com.pap.order.ports.api.OrderServicePort;
import com.pap.order.rest.dto.OrderDTO;
import com.pap.order.rest.mapper.OrderRestMapper;
import com.pap.product.model.PageableContent;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Validated
public class OrderController {
    private final OrderServicePort orderServicePort ;

    @PostMapping
    public List<OrderDomainObject> addOrders(@RequestBody List<@Valid OrderDTO> orders) {
        return orderServicePort.addOrders(OrderRestMapper.INSTANCE.toOrderDomainList(orders));
    }

    @PutMapping("/update/{id}")
    public OrderDomainObject updateOrder(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        return orderServicePort.updateOrder(id, OrderRestMapper.INSTANCE.toOrderDomain(orderDTO));

    }

    @GetMapping
    public PageableContent<OrderDomainObject> getOrders(
            @RequestParam @Min(value = 0 , message = "Page number must be greater than or equal 0") int page,
            @RequestParam int size,
            @RequestParam String filter
    ) {
        return orderServicePort.getOrders(page, size, filter);
    }
}
