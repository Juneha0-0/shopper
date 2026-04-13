package com.june.javaproject.shopper.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.june.javaproject.shopper.entity.Order;
import com.june.javaproject.shopper.entity.OrderStatus;
import com.june.javaproject.shopper.repository.OrderRepository;

@Service
public class OrderService {
    
    
    private final OrderRepository orderRepository;
    
    public OrderService(OrderRepository orderRepository, ProductService productService) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    
    // 定義檢查訂單狀態是否為 PENDING
    @Transactional
    public Order createOrder(Order order) {
        
        // 進行空值檢查
        Objects.requireNonNull(order, "訂單物件不能為空");
        
        // 進行訂單狀態檢查
        if (order.getStatus() != OrderStatus.PENDING) {
            throw new IllegalArgumentException("訂單狀態必須為 PENDING");
        }
        
        
        return orderRepository.save(order);
    }
}
