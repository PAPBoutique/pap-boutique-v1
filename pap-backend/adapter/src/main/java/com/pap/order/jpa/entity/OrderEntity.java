package com.pap.order.jpa.entity;

import com.pap.product.jpa.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderEntity {
    @Id
    @GeneratedValue
    private Long id;

    private Long productId ;

    private Long quantity ;

    private LocalDate creationDate ;
    private Double price ;

    private String client ;
    private Boolean checked ;
    @PrePersist
    public void prePersist() {
        if (creationDate == null) {
            creationDate = LocalDate.now();
        }
        checked = false ;
    }

}
