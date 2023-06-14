package com.pap.order.jpa.repository;

import com.pap.order.jpa.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity,Long> {
    @Query("SELECT o FROM OrderEntity o WHERE LOWER(o.client) LIKE %:filter%")
    Page<OrderEntity> findAllByClientContainingIgnoreCase(@Param("filter") String filter, Pageable pageable);
}
