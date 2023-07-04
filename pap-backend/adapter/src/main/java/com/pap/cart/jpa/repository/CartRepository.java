package com.pap.cart.jpa.repository;

import com.pap.cart.jpa.entity.CartEntity;
import com.pap.user.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CartRepository extends JpaRepository<CartEntity, Long> {
    List<CartEntity> findAllByUserOrderByCreatedDateDesc(UserEntity user);
    List<CartEntity> deleteByUser(UserEntity user);
}
