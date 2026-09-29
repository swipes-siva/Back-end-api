package com.example.entity.dto;

import jakarta.validation.constraints.NotBlank;

public class UserSearchRequest {

    @NotBlank(message = "Name is required")
    private String name;

    public UserSearchRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}