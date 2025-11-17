package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
}
