package com.ecommerce.ecommerce.model;

import com.ecommerce.ecommerce.enums.RoleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
/**
 * User Entity - Represents a person using the e-commerce system
 * This class stores all information about users (customers, sellers, admins)
 * Annotations explained:
 * Entity - Tells Spring this is a database table *  - Specifies the table name in database
 * Data - Lombok: Auto-generates getters, setters, toString, etc.
 * Builder - Lombok: Allows creating objects using builder pattern
 * NoArgsConstructor - Lombok: Creates constructor with no parameters
 * AllArgsConstructor - Lombok: Creates constructor with all parameters
 */
@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Id marks this field as the primary key (unique identifier)
    // @GeneratedValue tells database to auto-generate this value
    // GenerationType.IDENTITY means database handles the ID generation
    private Long id;

    @NotBlank// @NotBlank ensures this field cannot be empty or just whitespace
    private String fullName;

    // @Email validates that the string is a properly formatted email
    // @Column(unique = true) ensures no two users can have same email
    @Email
    @Column(unique = true, nullable = false)
    private String email;

    // Password should be encrypted before storing (done in service layer)
    @NotBlank
    private String password;

    private String phone;

    // @Enumerated tells JPA how to store the enum
    // EnumType.STRING stores the actual name (e.g., "ADMIN") instead of number
    // This makes the database more readable
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleType role; // ADMIN, SELLER, CUSTOMER

    // Boolean to track if user has verified their email
    // Default value is false until they verify
    @Column(nullable = false)
    private boolean isVerified = false;

    // @CreationTimestamp automatically sets this field when record is created
    @CreationTimestamp
    private LocalDateTime createdAt;

    // @UpdateTimestamp automatically updates this field when record is modified
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // Flag to enable/disable user accounts without deleting them
    @Column(nullable = false)
    private boolean isActive = true;

    private String profileImage; // URL or file path
}
/*
 * Author: Arnold Madamombe
 * Date: 12-Nov-2025
 * Project: E-commerce Spring Boot App
 * Description: User entity for the e-commerce application
 */