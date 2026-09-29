package org.example.entity.service;

import org.example.entity.dto.UserCreateRequest;
import org.example.entity.dto.UserResponse;
import org.example.entity.entity.User;
import org.example.entity.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // =========================
    // SEARCH USER BY NAME
    // =========================

    public List<UserResponse> getUsersByName(String name) {

        List<User> users =
                userRepository.findByNameContainingIgnoreCase(name);

        return users.stream()
                .map(user -> new UserResponse(
                        user.getUserId(),
                        user.getName(),
                        user.getPhone(),
                        user.getEmail(),
                        user.getCreatedAt()
                ))
                .toList();
    }


    // =========================
    // CREATE USER
    // =========================

    public UserResponse createUser(UserCreateRequest request) {

        // Check phone already exists
        if (userRepository.existsByPhone(request.getPhone())) {

            throw new RuntimeException(
                    "Phone number already registered"
            );
        }


        // Check email already exists
        if (userRepository.existsByEmail(request.getEmail())) {

            throw new RuntimeException(
                    "Email already registered"
            );
        }


        // Create User object
        User user = new User();

        user.setName(request.getName());
        user.setPhone(request.getPhone());
        user.setEmail(request.getEmail());


        // Hash PIN before storing
        user.setPinHash(
                passwordEncoder.encode(request.getPin())
        );


        user.setCreatedAt(LocalDateTime.now());


        // Save user
        User savedUser =
                userRepository.save(user);


        // Return response
        return new UserResponse(
                savedUser.getUserId(),
                savedUser.getName(),
                savedUser.getPhone(),
                savedUser.getEmail(),
                savedUser.getCreatedAt()
        );
    }
}