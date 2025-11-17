package com.ecommerce.ecommerce.model;

import com.ecommerce.ecommerce.enums.NotificationType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
/**
 * Notification Entity - Messages sent to users
 * System can send notifications about orders, promotions, etc.
 */
@Entity
@Table(name = "notifications")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Who receives this notification
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Detailed message
    @NotBlank
    @Column(nullable = false)
    private String title;


    @NotBlank
    @Column(nullable = false)
    private String message;

    // What kind of notification is this?
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType type; // ORDER, PROMOTION, SYSTEM

    // Has user seen this notification?
    @Column(nullable = false)
    private boolean isRead = false;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
