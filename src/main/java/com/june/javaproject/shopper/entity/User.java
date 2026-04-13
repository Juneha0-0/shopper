package com.june.javaproject.shopper.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, name = "username")
    private String username;
    
    @Column(nullable = false, name = "password")
    private String password;
    
    @Column(nullable = false, name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;
    
    // 第三方登陸
    @Enumerated(EnumType.STRING)
    @Column(name = "third_party_id")
    private AuthThirdPartyId thirdPartyId;

    // 用戶身份：買家、賣家
    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserType userType;
}
