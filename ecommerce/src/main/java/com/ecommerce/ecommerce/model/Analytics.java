package com.ecommerce.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
 * Analytics Entity - Business metrics and statistics
 * Stores aggregated data for reports and dashboards
 * Should be updated periodically (daily/weekly) via scheduled jobs
 */
@Entity
@Table(name = "analytics")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Analytics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Total number of registered users
    private Long totalUsers;

    // Total number of completed orders
    private Long totalOrders;

    // Total money earned from all orders
    private BigDecimal totalRevenue;


    @Lob
    @Column(columnDefinition = "TEXT") // or VARCHAR for smaller data
    private String topSellingProducts;

    @CreationTimestamp
    private LocalDateTime createdAt;
}

