package com.june.javaproject.shopper.service;

import com.june.javaproject.shopper.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.june.javaproject.shopper.entity.UserType;
import com.june.javaproject.shopper.repository.UserRepository;

import java.util.Optional;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    @Transactional
    public User register(User user) {
        Objects.requireNonNull(user, "用戶不能為 null");
        // 檢查用戶名是否已存在
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("用戶名已存在");
        }
        // 檢查郵箱是否已存在
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("郵箱已被註冊");
        }
        
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean checkUsernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    // 用戶可以選擇自己是買家或是賣家
    @Transactional
    public void setUserRole(long userId, String role) {
        if(role == null || role.trim().isEmpty()) {
            throw new RuntimeException("角色不能為空");
        }
        userRepository.findById(userId)
        .map(user -> {
            user.setUserType(UserType.valueOf(role.toUpperCase()));
            return userRepository.save(user);
        })
        .orElseThrow(() -> new RuntimeException("用戶不存在"));
    }
}