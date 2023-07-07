package com.pap.cart.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pap.product.jpa.entity.ProductEntity;
import com.pap.user.jpa.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="cart")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;
    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE})
    private ProductEntity product;
    @OneToOne
    private UserEntity user;
    private LocalDateTime createdDate;
    private int quantity;

    @PrePersist
    public void prePersist() {
        if (createdDate == null) {
            createdDate = LocalDateTime.now();

        }
    }

}