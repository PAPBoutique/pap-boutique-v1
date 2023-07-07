package com.pap.cart.rest.controller;


import com.pap.cart.model.CartDomainObject;
import com.pap.cart.ports.api.CartServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartServicePort cartServicePort;
    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping({"/addToCart/{productId}"})
    public CartDomainObject addToCart(@PathVariable(name = "productId") Long productId) {
        return cartServicePort.addToCart(productId);
    }

    @PreAuthorize("hasRole('CLIENT')")
    @DeleteMapping({"/deleteCartItem/{cartId}"})
    public void deleteCartItem(@PathVariable(name = "cartId") Long productId) {
        cartServicePort.deleteCartItem(productId);
    }

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping({"/getCartDetails"})
    public List<CartDomainObject> getCartDetails() {
        return cartServicePort.getCartDetails();
    }


}
