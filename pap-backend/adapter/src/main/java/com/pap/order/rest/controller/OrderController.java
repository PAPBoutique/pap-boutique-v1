package com.pap.order.rest.controller;

import com.pap.order.jpa.repository.OrderRepository;
import com.pap.order.model.OrderDomainObject;
import com.pap.order.ports.api.OrderServicePort;
import com.pap.order.rest.dto.OrderDTO;
import com.pap.order.rest.mapper.OrderRestMapper;
import com.pap.product.model.PageableContent;
import com.pap.product.model.ProductDomainObject;
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

    @PostMapping("/check/{id}")
    public String checkOrder(@PathVariable Long id){
        orderServicePort.checkOrder(id);
        return "Order checked !!";
    }

    @GetMapping
    public PageableContent<OrderDomainObject> getOrders(
            @RequestParam @Min(value = 0 , message = "Page number must be greater than or equal 0") int page,
            @RequestParam int size,
            @RequestParam String filterValue
    ) {
        return orderServicePort.getOrders(page, size, filterValue);
    }

    @GetMapping("/product/{id}")
    public ProductDomainObject getOrderProduct(@PathVariable long id){
        return orderServicePort.getProduct(id);
    }

    @GetMapping("/countOrders")
    public  Long getTotalOrders()
    {
        return orderServicePort.getTotalOrders();
    }

    @GetMapping("/countByMonth")
    public List<Object[]> countOrderByCheckedAndMonth() {
        return orderServicePort.countOrderByCheckedAndMonth();
    }

    @GetMapping("/revenue")
    public Double getRevenue()
    {
        return orderServicePort.getTotalPrice();
    }
}
