package com.june.javaproject.shopper.service;

import com.june.javaproject.shopper.entity.OrderStatus;
import com.june.javaproject.shopper.entity.Product;
import com.june.javaproject.shopper.entity.User;
import com.june.javaproject.shopper.entity.UserType;
import com.june.javaproject.shopper.repository.ProductRepository;
import com.june.javaproject.shopper.repository.UserRepository;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;

@Service
public class ProductService {
    
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ProductService(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(long id) {
        return productRepository.findById(id).orElse(null);
    }

    // 定義只有賣家才能對產品進行修改、新增等操作
    private void validateSellerRole(long userId) {
        User user = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("找不到該用戶。"));
    if (user.getUserType() != UserType.SELLER){
        throw new RuntimeException("用戶不是賣家。");
    }
    }
    
    @Transactional
    public Product createProduct(long userId, Product product) {
        // 解決產品空值問題
        Objects.requireNonNull(product, "產品不能為 null");
        validateSellerRole(userId);
        return productRepository.save(product);
    }

    @Transactional
    public Product updateProduct(long userId, Product product) {
        Objects.requireNonNull(product, "產品不能為 null");
        validateSellerRole(userId);
        return productRepository.save(product);
    }

    @Transactional
    public void deleteProduct(long userId, long id) {
        validateSellerRole(userId);
        productRepository.deleteById(id);
    }

    @Transactional
    public void decreaseStock(long productId, int quantity, OrderStatus status) {
        // 實現庫存扣減邏輯
        if (status != OrderStatus.PENDING) {
            return;
        }
        
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("找不到該產品。"));
        product.setStock(product.getStock() - quantity);
        
        if (product.getStock() < 0) {
            throw new RuntimeException("庫存不足。");
        }

        product.setStock(product.getStock() - quantity);
        productRepository.save(product);
    }
}
