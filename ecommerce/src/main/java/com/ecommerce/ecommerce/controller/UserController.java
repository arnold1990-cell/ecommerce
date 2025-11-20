package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.model.User;
import com.ecommerce.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * UserController - Exposes REST API endpoints for managing users.
 * This layer communicates with UserService and sends results to the client.
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * CREATE a new user
     * POST: /api/users
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User created = userService.createUser(user);
        return ResponseEntity.ok(created);
    }

    /**
     * GET ALL users
     * GET: /api/users
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    /**
     * GET USER BY ID
     * GET: /api/users/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * UPDATE USER
     * PUT: /api/users/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable Long id,
            @RequestBody User userDetails
    ) {
        User updated = userService.updateUser(id, userDetails);
        return ResponseEntity.ok(updated);
    }

    /**
     * DELETE USER
     * DELETE: /api/users/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * VERIFY USER (email confirmation)
     * PUT: /api/users/{id}/verify
     */
    @PutMapping("/{id}/verify")
    public ResponseEntity<User> verifyUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.verifyUser(id));
    }

    /**
     * ACTIVATE OR DEACTIVATE USER
     * PUT: /api/users/{id}/activate?status=true
     */
    @PutMapping("/{id}/activate")
    public ResponseEntity<User> setUserActiveStatus(
            @PathVariable Long id,
            @RequestParam boolean status
    ) {
        return ResponseEntity.ok(userService.setActiveStatus(id, status));
    }

    /**
     * FIND USER BY EMAIL
     * GET: /api/users/find?email=example@mail.com
     */
    @GetMapping("/find")
    public ResponseEntity<User> findByEmail(@RequestParam String email) {
        return userService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

