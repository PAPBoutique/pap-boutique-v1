package com.pap.cart.jpa;

import com.pap.cart.jpa.entity.CartEntity;
import com.pap.cart.jpa.mapper.CartMapper;
import com.pap.cart.jpa.repository.CartRepository;
import com.pap.cart.model.CartDomainObject;
import com.pap.cart.ports.spi.CartJpaPort;
import com.pap.product.jpa.entity.ProductEntity;
import com.pap.product.jpa.mapper.ProductMapper;
import com.pap.product.jpa.repository.ProductRepository;
import com.pap.product.model.ProductDomainObject;
import com.pap.user.jpa.entity.UserEntity;
import com.pap.user.jpa.mapper.UserMapper;
import com.pap.user.jpa.repository.UserRepository;
import com.pap.user.model.UserDomainObject;
import com.pap.user.security.jwt.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CartJpaAdapter implements CartJpaPort{

    private  final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;




      @Transactional
    @Override
    public CartDomainObject addToCart(Long productId) {
        ProductEntity product = productRepository.findById(productId).orElse(null);
        String username = JwtAuthFilter.CURRENT_USER;
        UserEntity user = null;

        if (username != null) {
            user = userRepository.findByUsername(username).orElse(null);
        }

        if (product != null && user != null) {
            List<CartEntity> cartList = cartRepository.findByUser(user);
            CartEntity existingCartItem = cartList.stream()
                    .filter(x -> x.getProduct().getId() == productId)
                    .findFirst()
                    .orElse(null);

            if (existingCartItem != null) {
                existingCartItem.setQuantity(existingCartItem.getQuantity() + 1);
                CartEntity savedCartEntity = cartRepository.save(existingCartItem);
                CartDomainObject savedCartDomainObject = CartMapper.INSTANCE.toCartDomain(savedCartEntity);
                return savedCartDomainObject;
            } else {
               ProductDomainObject productDomainObject = ProductMapper.INSTANCE.productToProductDomain(product);
                UserDomainObject userDomainObject = UserMapper.INSTANCE.toUserDomain(user);
                CartDomainObject cartDomainObject = new CartDomainObject(productDomainObject, userDomainObject, 1);
                CartEntity cartEntity = CartMapper.INSTANCE.toCartEntity(cartDomainObject);
                CartEntity savedCartEntity = cartRepository.save(cartEntity);
                CartDomainObject savedCartDomainObject = CartMapper.INSTANCE.toCartDomain(savedCartEntity);
                return savedCartDomainObject;
            }
        }

        return null;
    }
    @Transactional
    @Override
    public void deleteCartItem(Long productId) {
        String username = JwtAuthFilter.CURRENT_USER;
        UserEntity user = null;

        if (username != null) {
            user = userRepository.findByUsername(username).orElse(null);
        }

        if (user != null) {
            List<CartEntity> cartList = cartRepository.findByUser(user);
            CartEntity cartItemToDelete = cartList.stream()
                    .filter(x -> x.getProduct().getId() == productId)
                    .findFirst()
                    .orElse(null);

            if (cartItemToDelete != null) {
                cartRepository.delete(cartItemToDelete);
            }
        }
    }

    @Transactional
    @Override
    public List<CartDomainObject> getCartDetails() {
        String username = JwtAuthFilter.CURRENT_USER;
        UserEntity user = userRepository.findByUsername(username).orElse(null);

        if (user != null) {
            List<CartEntity> cartEntities = cartRepository.findByUser(user);
            return CartMapper.INSTANCE.toCartDomainList(cartEntities);
        }

        return Collections.emptyList();
    }
}
