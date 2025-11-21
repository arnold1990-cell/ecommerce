package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
    List<Notification> findByUserId(Long userId);

    long countByUserIdAndIsReadFalse(Long userId);
}
