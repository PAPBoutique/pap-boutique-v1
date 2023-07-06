package com.pap.cart.service;

import com.pap.cart.model.CartDomainObject;
import com.pap.cart.ports.api.CartServicePort;
import com.pap.cart.ports.spi.CartJpaPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService implements CartServicePort {

    private final CartJpaPort cartJpaPort;

    @Override
    public CartDomainObject addToCart(Long productId) {
        return cartJpaPort.addToCart(productId);
    }

    @Override
    public void deleteCartItem(Long cartId) {
            cartJpaPort.deleteCartItem(cartId);
    }

    @Override
    public List<CartDomainObject> getCartDetails() {
        return cartJpaPort.getCartDetails();
    }
}
