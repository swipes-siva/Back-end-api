package org.example.entity.repository;

import org.example.entity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Search users by name
    List<User> findByNameContainingIgnoreCase(String name);

    // Find user by phone
    Optional<User> findByPhone(String phone);

    // Check whether phone already exists
    boolean existsByPhone(String phone);

    // Check whether email already exists
    boolean existsByEmail(String email);
}