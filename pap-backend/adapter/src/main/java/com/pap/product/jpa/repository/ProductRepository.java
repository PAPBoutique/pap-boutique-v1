package com.pap.product.jpa.repository;

import com.pap.product.jpa.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
    @Query("SELECT p FROM ProductEntity p WHERE LOWER(p.name) LIKE %:filter%")
    Page<ProductEntity> findAllByNameContainingIgnoreCase(@Param("filter") String filter, Pageable pageable);
}
