package com.school.cafeteria.model;

import java.time.LocalDateTime;

public class OrderMessage {
    private int id;
    private int orderId;
    private String sender;
    private String message;
    private LocalDateTime createdAt;

    public OrderMessage() {}
    public OrderMessage(int id, int orderId, String sender, String message, LocalDateTime createdAt) {
        this.id = id; this.orderId = orderId; this.sender = sender; this.message = message; this.createdAt = createdAt;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public String getSender() { return sender; }
    public void setSender(String sender) { this.sender = sender; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
