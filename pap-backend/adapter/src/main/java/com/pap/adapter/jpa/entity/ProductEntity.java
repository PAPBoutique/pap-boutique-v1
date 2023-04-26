package com.pap.adapter.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String name;
    private String description;
    private Integer quantity;
    private  Double price;
    private Date CreatedDate;
    @PrePersist
    public void prePersist() {
        if (CreatedDate == null) {
            CreatedDate = new Date();
        }
    }
}
