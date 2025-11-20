package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.dto.UserDto;
import com.ecommerce.ecommerce.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {



       UserDto toDto(User user);

        User  toEntity(UserDto userDto);



}
