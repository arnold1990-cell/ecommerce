package com.ecommerce.ecommerce.mapping;

import com.ecommerce.ecommerce.dto.AddressDto;
import com.ecommerce.ecommerce.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressMapper {

@Mapping(source = "user.id",target = "userId")
        AddressDto toDto(Address address);
    @Mapping(source = "userId",target = "user.id")
         Address toEntity(AddressDto addressDto);

    }

