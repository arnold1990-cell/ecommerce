package com.ecommerce.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
/**
 * Category Entity - Organizes products into categories
 *
 * Supports nested categories (subcategories) through self-referencing
 * Example: Electronics > Phones > Smartphones
 */
@Entity
@Table(name = "categories")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Category name must be unique to avoid confusion
    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    // Self-referencing relationship for subcategories
    // A category can have a parent category (null if it's a top-level category)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory;

    // List of all child categories under this category
    // mappedBy = "parentCategory" means Category entity manages this relationship
    // cascade = CascadeType.ALL means operations on parent affect children
    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL)
    private List<Category> subcategories;

    private String imageUrl;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
/**
 * Author: Arnold Madamombe
 * Date: 12-Nov-2025
 * Description: Full set of e-commerce entities
 */
