package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.dto.UserDto;
import com.ecommerce.ecommerce.enums.RoleType;
import com.ecommerce.ecommerce.model.User;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-24T22:02:06+0200",
    comments = "version: 1.6.2, compiler: javac, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        Long id = null;
        String fullName = null;
        String email = null;
        String phone = null;
        RoleType role = null;
        String profileImage = null;
        LocalDateTime createdAt = null;
        LocalDateTime updatedAt = null;

        id = user.getId();
        fullName = user.getFullName();
        email = user.getEmail();
        phone = user.getPhone();
        role = user.getRole();
        profileImage = user.getProfileImage();
        createdAt = user.getCreatedAt();
        updatedAt = user.getUpdatedAt();

        boolean isVerified = false;
        boolean isActive = false;

        UserDto userDto = new UserDto( id, fullName, email, phone, role, isVerified, isActive, profileImage, createdAt, updatedAt );

        return userDto;
    }

    @Override
    public User toEntity(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( userDto.id() );
        user.fullName( userDto.fullName() );
        user.email( userDto.email() );
        user.phone( userDto.phone() );
        user.role( userDto.role() );
        user.isVerified( userDto.isVerified() );
        user.createdAt( userDto.createdAt() );
        user.updatedAt( userDto.updatedAt() );
        user.isActive( userDto.isActive() );
        user.profileImage( userDto.profileImage() );

        return user.build();
    }
}
