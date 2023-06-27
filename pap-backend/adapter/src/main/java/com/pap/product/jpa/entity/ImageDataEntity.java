package com.pap.product.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ImageData")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    @Lob
    @Column(length = 2140000000)
    private byte[] picByte;

    public ImageDataEntity(String name, String type, byte[] picByte) {
        this.name = name;
        this.type = type;
        this.picByte = picByte;
    }
    
}