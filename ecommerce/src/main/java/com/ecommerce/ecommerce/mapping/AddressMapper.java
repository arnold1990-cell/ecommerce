package com.ecommerce.ecommerce.mapping;

import com.ecommerce.ecommerce.dto.AddressDto;
import com.ecommerce.ecommerce.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    // Converts Address entity → AddressDto
    // Maps the nested User object's ID (address.user.id) to a simple userId field on the DTO
    @Mapping(source = "user.id", target = "userId")
    AddressDto toDto(Address address);

    // Converts AddressDto → Address entity
    // Maps the DTO's userId back into the nested User object (address.user.id)
    // This allows the service layer to reconstruct the relationship without manually creating a User object
    @Mapping(source = "userId", target = "user.id")
    Address toEntity(AddressDto addressDto);

}



