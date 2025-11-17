package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<Wishlist,Long> {
}
