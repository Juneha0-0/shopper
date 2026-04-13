package com.june.javaproject.shopper.repository;

import java.util.Optional;

import com.june.javaproject.shopper.entity.Order;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

    Optional<Order> findByOrderNumber(String orderNumber);
    
}

