package com.pap.cart.model;

import com.pap.product.model.ProductDomainObject;
import com.pap.user.model.UserDomainObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDomainObject {
    private Long id;
    private LocalDate createdDate;
    private ProductDomainObject product;
    private UserDomainObject user;
    private int quantity;

}
