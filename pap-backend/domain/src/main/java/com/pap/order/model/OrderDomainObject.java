package com.pap.order.model;


import com.pap.product.model.ProductDomainObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class OrderDomainObject {
    private Long id ;
    private ProductDomainObject product ;
    private Long quantity ;
    private Date creationDate ;
    private double price ;
}
