package com.ecommerce.ecommerce.model;

import com.ecommerce.ecommerce.enums.RoleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String fullName;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank
    private String password;

    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleType role;  // ADMIN, SELLER, CUSTOMER

    @Column(nullable = false)
    private boolean isVerified = false;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private boolean isActive = true;

    private String profileImage;

    /** -------------- Spring Security Methods -------------- */

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;   // email is your login field
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;    // you are not using expiring accounts
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive; // locked if deactivated
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // password never expires
    }

    @Override
    public boolean isEnabled() {
        return isVerified && isActive; // better control
    }
}

/*
 * Author: Arnold Madamombe
 * Date: 12-Nov-2025
 * Project: E-commerce Spring Boot App
 * Description: User entity for the e-commerce application
 */