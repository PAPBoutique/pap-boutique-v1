package com.pap.order.jpa.repository;

import com.pap.order.jpa.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity,Long> {
    @Query("SELECT o FROM OrderEntity o WHERE LOWER(o.client) LIKE %:filter%")
    Page<OrderEntity> findAllByClientContainingIgnoreCase(@Param("filter") String filter, Pageable pageable);

    @Query("SELECT EXTRACT(MONTH FROM o.creationDate), o.checked, COUNT(o) FROM OrderEntity o GROUP BY EXTRACT(MONTH FROM o.creationDate), o.checked")
    List<Object[]> countOrderByCheckedAndMonth();

    @Query("SELECT SUM(o.price) FROM OrderEntity o")
    Double getTotalPrice();

}
