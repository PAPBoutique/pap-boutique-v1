package com.pap.cart.ports.api;

import com.pap.cart.model.CartDomainObject;

import java.util.List;

public interface CartServicePort {

    CartDomainObject addToCart(Long productId);
    void deleteCartItem(Long cartId);

    List<CartDomainObject> getCartDetails();
}
