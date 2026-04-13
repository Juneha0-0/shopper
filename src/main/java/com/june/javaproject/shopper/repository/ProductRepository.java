package com.june.javaproject.shopper.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.june.javaproject.shopper.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
}

    

