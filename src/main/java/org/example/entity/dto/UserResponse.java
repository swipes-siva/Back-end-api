package org.example.entity.dto;

import java.time.LocalDateTime;

public class UserResponse {

    private Long userId;
    private String name;
    private String phone;
    private String email;
    private LocalDateTime createdAt;

    public UserResponse(
            Long userId,
            String name,
            String phone,
            String email,
            LocalDateTime createdAt) {

        this.userId = userId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.createdAt = createdAt;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}