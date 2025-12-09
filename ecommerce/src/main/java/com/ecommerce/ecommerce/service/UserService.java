package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.model.User;
import com.ecommerce.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /** ----------------- User Management ----------------- */

    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists: " + user.getEmail());
        }

        // Encrypt password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User updateUser(Long id, User updatedUser) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));

        existing.setFullName(updatedUser.getFullName());
        existing.setPhone(updatedUser.getPhone());
        existing.setRole(updatedUser.getRole());
        existing.setProfileImage(updatedUser.getProfileImage());

        // Update password only if provided
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isBlank()) {
            existing.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

        return userRepository.save(existing);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User verifyUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
        user.setVerified(true);
        return userRepository.save(user);
    }

    public User setActiveStatus(Long id, boolean active) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
        user.setActive(active);
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /** ----------------- Password Management ----------------- */

    public void changePassword(String currentPassword, String newPassword, String confirmPassword, Principal connectedUser) {
        User user = (User) connectedUser; // Use your authentication principal

        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new IllegalStateException("Current password is incorrect");
        }

        if (!newPassword.equals(confirmPassword)) {
            throw new IllegalStateException("New passwords do not match");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
