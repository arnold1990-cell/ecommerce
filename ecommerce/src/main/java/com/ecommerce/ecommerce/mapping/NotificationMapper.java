package com.ecommerce.ecommerce.mapping;

import com.ecommerce.ecommerce.dto.NotificationDto;
import com.ecommerce.ecommerce.model.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    // Converts Notification entity → NotificationDto
    // Maps the nested User object's ID (notification.user.id) to a simple userId field on the DTO
    // Useful for sending notification data to the frontend without exposing the full User entity
    @Mapping(source = "user.id", target = "userId")
    NotificationDto toDto(Notification notification);

    // Converts NotificationDto → Notification entity
    // Maps the DTO's userId back into the nested User object (notification.user.id)
    // Allows reconstruction of the User relationship when persisting or updating notifications
    @Mapping(source = "userId", target = "user.id")
    Notification toEntity(NotificationDto notificationDto);

}

