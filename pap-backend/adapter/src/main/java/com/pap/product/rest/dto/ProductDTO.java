package com.pap.product.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {

    @NotBlank(message = "Please enter a product name")
    private String name ;

    @NotBlank(message = "Please enter a product description")
    private String description;
    @NotNull(message = "Quantity cannot be null" )
    @Min(value = 1 , message = "Product quantity must be greater than 0" )
    private Integer quantity;
    @NotNull(message = "Price cannot be null" )
    @Min(value = 0 , message = "Product's price must be greater than 0")
    private Double price ;
}
