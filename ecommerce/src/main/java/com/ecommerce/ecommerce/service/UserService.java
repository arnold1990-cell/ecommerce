package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.model.User;
import com.ecommerce.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * UserService - Handles all business logic for User entity.
 * This layer ensures clean separation between controller and repository.
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // For encrypting passwords

    /**
     * Create a new user
     * - Checks if email already exists
     * - Encrypts password before saving
     */
    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists: " + user.getEmail());
        }

        // Encrypt password to avoid storing plain text
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    /**
     * Retrieve all users
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Find a user by ID
     */
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Update user details (except password unless explicitly changed)
     */
    public User updateUser(Long id, User updatedUser) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));

        existing.setFullName(updatedUser.getFullName());
        existing.setPhone(updatedUser.getPhone());
        existing.setRole(updatedUser.getRole());
        existing.setProfileImage(updatedUser.getProfileImage());

        // Only update password if user provided a new one
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isBlank()) {
            existing.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

        return userRepository.save(existing);
    }

    /**
     * Delete a user (soft delete recommended in real apps)
     */
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * Mark user as verified (after email confirmation)
     */
    public User verifyUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));

        user.setVerified(true);
        return userRepository.save(user);
    }

    /**
     * Activate or deactivate account without deleting it
     */
    public User setActiveStatus(Long id, boolean active) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));

        user.setActive(active);
        return userRepository.save(user);
    }

    /**
     * Find user by email (used for login and password reset)
     */
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
