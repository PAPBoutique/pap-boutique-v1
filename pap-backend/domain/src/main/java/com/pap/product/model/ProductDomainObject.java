package com.pap.product.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ProductDomainObject {
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private LocalDateTime createdDate;


}
