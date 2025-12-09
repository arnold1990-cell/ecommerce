package com.ecommerce.ecommerce.mapping;

import com.ecommerce.ecommerce.dto.NotificationDto;
import com.ecommerce.ecommerce.enums.NotificationType;
import com.ecommerce.ecommerce.model.Notification;
import com.ecommerce.ecommerce.model.User;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-25T12:00:02+0200",
    comments = "version: 1.6.2, compiler: javac, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class NotificationMapperImpl implements NotificationMapper {

    @Override
    public NotificationDto toDto(Notification notification) {
        if ( notification == null ) {
            return null;
        }

        Long userId = null;
        Long id = null;
        String title = null;
        String message = null;
        NotificationType type = null;
        LocalDateTime createdAt = null;

        userId = notificationUserId( notification );
        id = notification.getId();
        title = notification.getTitle();
        message = notification.getMessage();
        type = notification.getType();
        createdAt = notification.getCreatedAt();

        boolean isRead = false;

        NotificationDto notificationDto = new NotificationDto( id, userId, title, message, type, isRead, createdAt );

        return notificationDto;
    }

    @Override
    public Notification toEntity(NotificationDto notificationDto) {
        if ( notificationDto == null ) {
            return null;
        }

        Notification.NotificationBuilder notification = Notification.builder();

        notification.user( notificationDtoToUser( notificationDto ) );
        notification.id( notificationDto.id() );
        notification.title( notificationDto.title() );
        notification.message( notificationDto.message() );
        notification.type( notificationDto.type() );
        notification.isRead( notificationDto.isRead() );
        notification.createdAt( notificationDto.createdAt() );

        return notification.build();
    }

    private Long notificationUserId(Notification notification) {
        User user = notification.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getId();
    }

    protected User notificationDtoToUser(NotificationDto notificationDto) {
        if ( notificationDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( notificationDto.userId() );

        return user.build();
    }
}
