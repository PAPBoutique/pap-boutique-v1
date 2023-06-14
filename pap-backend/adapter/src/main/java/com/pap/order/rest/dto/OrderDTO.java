package com.pap.order.rest.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {
    @NotNull(message = "Product Id  cannot be null" )
    private Long productId ;

    @NotNull(message = "Quantity cannot be null" )
    @Min(value = 1 , message = "Product quantity must be greater than 0" )
    private Long quantity ;

    @NotNull(message = "Client cannot be null" )
    private String client ;
}
