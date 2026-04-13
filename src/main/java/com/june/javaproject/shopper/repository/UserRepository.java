package com.june.javaproject.shopper.repository;

import com.june.javaproject.shopper.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // 根據郵箱查詢用戶
    Optional<User> findByEmail(String email);

    // 根據用戶名查詢用戶
    Optional<User> findByUsername(String username);

    // 根據手機號查詢用戶
    Optional<User> findByPhone(String phone);

    // 查詢該郵箱是否已存在
    boolean existsByEmail(String email);
    
    // 查詢該用戶名是否已存在
    boolean existsByUsername(String username);
    
    // 查詢該手機號是否已存在
    boolean existsByPhone(String phone);
}


