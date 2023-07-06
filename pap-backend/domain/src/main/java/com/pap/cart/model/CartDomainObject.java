package com.pap.cart.model;

import com.pap.product.model.ProductDomainObject;
import com.pap.user.model.UserDomainObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDomainObject {
    private Long cartId;
    private LocalDateTime createdDate;
    private ProductDomainObject product;
    private UserDomainObject user;
    private int quantity;

    public CartDomainObject(ProductDomainObject product, UserDomainObject user) {
        this.product = product;
        this.user = user;
    }

}
