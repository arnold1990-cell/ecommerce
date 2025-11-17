package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {
}
