package com.pap.adapter.dto;

import lombok.*;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ProductDTO {
        private String name;
        private String description;
        private Double price;
        private Integer quantity;


}
