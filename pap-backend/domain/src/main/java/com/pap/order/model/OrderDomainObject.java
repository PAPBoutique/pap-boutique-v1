package com.pap.order.model;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@Builder
public class OrderDomainObject {
    private Long id ;
    private Long productId ;
    private Long quantity ;
    private LocalDate creationDate ;
    private Double price ;
    private String client ;
    private Boolean checked ;
}
