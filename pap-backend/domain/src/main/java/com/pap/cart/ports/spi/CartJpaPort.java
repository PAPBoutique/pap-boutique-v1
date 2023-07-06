package com.pap.cart.ports.spi;

import com.pap.cart.model.CartDomainObject;

import java.util.List;

public interface CartJpaPort {

    CartDomainObject addToCart(Long productId);
    void deleteCartItem(Long cartId);

    List<CartDomainObject> getCartDetails();
}
