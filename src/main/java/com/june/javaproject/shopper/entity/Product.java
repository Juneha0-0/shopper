package com.june.javaproject.shopper.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product")
@Setter
@Getter
public class Product {

    @Column(name = "id", nullable = false)
    private Long id;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "description", nullable = false)
    private String description;
    
    @Column(name = "price", nullable = false, columnDefinition = "VARCHAR(255) CHECK (price >= 0)")
    private String price;
    
    //庫存數量，限制不能為負數
    @Column(name = "stock", nullable = false, columnDefinition = "INTEGER CHECK (stock >= 0)")
    private Integer stock;
    
    // 價格歷史記錄，用於記錄價格變更歷史
    @Column(name = "price_history", columnDefinition = "TEXT")
    private String priceHistory = "";

        // 自定義 Setter 來追蹤變更
    public void setPrice(Double newPrice) {
        if (this.price != null && !this.price.equals(String.valueOf(newPrice))) {
            // 如果價格變更，將舊價格存入歷史（例如以逗號分隔）
            if (this.priceHistory == null || this.priceHistory.isEmpty()) {
                this.priceHistory = String.valueOf(this.price);
            } else {
                this.priceHistory += ", " + this.price;
            }
        }
        this.price = String.valueOf(newPrice);
    }
}
