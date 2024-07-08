package com.example.online_shop.dto;

import com.example.online_shop.models.Order;
import com.example.online_shop.models.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PaymentDto {
    private int id;
    private User user;
    private double amount;
    private LocalDateTime paidAt;
    private List<Order> orders;

    public PaymentDto(int id, User user, double amount, LocalDateTime paidAt, List<Order> orders) {
        this.id = id;
        this.user = user;
        this.amount = amount;
        this.paidAt = paidAt;
        this.orders = orders;
    }

    public PaymentDto(User user, double amount, LocalDateTime paidAt, List<Order> orders) {
        this.user = user;
        this.amount = amount;
        this.paidAt = paidAt;
        this.orders = orders;
    }

    public PaymentDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(LocalDateTime paidAt) {
        this.paidAt = paidAt;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
