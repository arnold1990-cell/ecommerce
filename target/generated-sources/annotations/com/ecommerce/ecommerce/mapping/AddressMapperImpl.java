package com.ecommerce.ecommerce.mapping;

import com.ecommerce.ecommerce.dto.AddressDto;
import com.ecommerce.ecommerce.model.Address;
import com.ecommerce.ecommerce.model.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-25T12:00:02+0200",
    comments = "version: 1.6.2, compiler: javac, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class AddressMapperImpl implements AddressMapper {

    @Override
    public AddressDto toDto(Address address) {
        if ( address == null ) {
            return null;
        }

        Long userId = null;
        Long id = null;
        String street = null;
        String city = null;
        String country = null;
        String postalCode = null;
        String phone = null;
        LocalDateTime createdAt = null;
        LocalDateTime updatedAt = null;

        userId = addressUserId( address );
        id = address.getId();
        street = address.getStreet();
        city = address.getCity();
        country = address.getCountry();
        postalCode = address.getPostalCode();
        phone = address.getPhone();
        createdAt = address.getCreatedAt();
        updatedAt = address.getUpdatedAt();

        boolean isDefault = false;

        AddressDto addressDto = new AddressDto( id, userId, street, city, country, postalCode, phone, isDefault, createdAt, updatedAt );

        return addressDto;
    }

    @Override
    public Address toEntity(AddressDto addressDto) {
        if ( addressDto == null ) {
            return null;
        }

        Address.AddressBuilder address = Address.builder();

        address.user( addressDtoToUser( addressDto ) );
        address.id( addressDto.id() );
        address.street( addressDto.street() );
        address.city( addressDto.city() );
        address.country( addressDto.country() );
        address.postalCode( addressDto.postalCode() );
        address.phone( addressDto.phone() );
        address.isDefault( addressDto.isDefault() );
        address.createdAt( addressDto.createdAt() );
        address.updatedAt( addressDto.updatedAt() );

        return address.build();
    }

    @Override
    public List<AddressDto> toDtoList(List<Address> addresses) {
        if ( addresses == null ) {
            return null;
        }

        List<AddressDto> list = new ArrayList<AddressDto>( addresses.size() );
        for ( Address address : addresses ) {
            list.add( toDto( address ) );
        }

        return list;
    }

    private Long addressUserId(Address address) {
        User user = address.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getId();
    }

    protected User addressDtoToUser(AddressDto addressDto) {
        if ( addressDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( addressDto.userId() );

        return user.build();
    }
}
