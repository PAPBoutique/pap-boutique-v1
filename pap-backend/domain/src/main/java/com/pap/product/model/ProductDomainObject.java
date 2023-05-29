package com.pap.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class ProductDomainObject {
    private Long id ;
    private String name ;
    private String description;
    private Integer quantity;
    private Double price ;
    private LocalDate createdDate ;


}
