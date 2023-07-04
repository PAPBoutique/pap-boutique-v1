package com.pap.cart.rest.controller;

import com.pap.cart.jpa.entity.CartEntity;
import com.pap.cart.jpa.repository.CartRepository;
import com.pap.product.jpa.entity.ProductEntity;
import com.pap.user.jpa.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {

}
