package com.pap.product.jpa.repository;

import com.pap.product.jpa.entity.ImageDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageDataEntity, Long> {
}
