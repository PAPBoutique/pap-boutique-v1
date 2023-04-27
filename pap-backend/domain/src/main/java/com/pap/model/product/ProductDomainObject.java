package com.pap.model.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDomainObject {
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private LocalDateTime createdDate;
}
