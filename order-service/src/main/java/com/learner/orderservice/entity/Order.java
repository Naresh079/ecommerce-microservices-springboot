package com.learner.orderservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productId;
    private String status;

    public Order() {}

    public Order(String productId, String status) {
        this.productId = productId;
        this.status = status;
    }

    // getters and setters
    public Long getId() { return id; }
    public String getProductId() { return productId; }
    public String getStatus() { return status; }
    public void setId(Long id) { this.id = id; }
    public void setProductId(String productId) { this.productId = productId; }
    public void setStatus(String status) { this.status = status; }
}
