package org.example.entity.controller;

import org.example.entity.dto.UserCreateRequest;
import org.example.entity.dto.UserResponse;
import org.example.entity.dto.UserSearchRequest;
import org.example.entity.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // SEARCH USER
    @GetMapping("/search")
    public ResponseEntity<List<UserResponse>> getUsersByName(
            @RequestBody UserSearchRequest request) {

        List<UserResponse> users =
                userService.getUsersByName(request.getName());

        return ResponseEntity.ok(users);
    }

    // CREATE USER
    @PostMapping
    public ResponseEntity<UserResponse> createUser(
            @RequestBody UserCreateRequest request) {

        UserResponse response =
                userService.createUser(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}