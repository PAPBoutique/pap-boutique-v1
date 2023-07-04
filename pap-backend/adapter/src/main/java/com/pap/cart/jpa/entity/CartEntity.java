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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="cart")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "created_date")
    private LocalDate createdDate;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductEntity product;
    @JsonIgnore
    @OneToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private UserEntity user;
    private int quantity;

    public CartEntity(ProductEntity product, int quantity, UserEntity user){
        this.user = user;
        this.product = product;
        this.quantity = quantity;
        this.createdDate = LocalDate.now();
    }
}