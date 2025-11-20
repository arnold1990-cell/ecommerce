package com.ecommerce.ecommerce.mapping;

import com.ecommerce.ecommerce.dto.TransactionDto;
import com.ecommerce.ecommerce.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    // Converts Transaction entity → TransactionDto
    // Maps the nested User and Order IDs to simple userId and orderId fields on the DTO
    // Useful for sending transaction data to the frontend without exposing full User or Order entities
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "order.id", target = "orderId")
    TransactionDto toDto(Transaction transaction);

    // Converts TransactionDto → Transaction entity
    // Maps DTO's userId and orderId back into the nested User and Order objects
    // Allows reconstruction of relationships when persisting or updating transactions
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "orderId", target = "order.id")
    Transaction toEntity(TransactionDto transactionDto);

}



