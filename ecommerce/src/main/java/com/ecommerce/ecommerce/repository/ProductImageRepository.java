package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage,Long> {
}
