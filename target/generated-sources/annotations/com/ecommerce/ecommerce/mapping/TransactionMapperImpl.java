package com.ecommerce.ecommerce.mapping;

import com.ecommerce.ecommerce.dto.TransactionDto;
import com.ecommerce.ecommerce.enums.PaymentProvider;
import com.ecommerce.ecommerce.enums.TransactionStatus;
import com.ecommerce.ecommerce.model.Order;
import com.ecommerce.ecommerce.model.Transaction;
import com.ecommerce.ecommerce.model.User;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-24T22:02:06+0200",
    comments = "version: 1.6.2, compiler: javac, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class TransactionMapperImpl implements TransactionMapper {

    @Override
    public TransactionDto toDto(Transaction transaction) {
        if ( transaction == null ) {
            return null;
        }

        Long userId = null;
        Long orderId = null;
        Long id = null;
        PaymentProvider paymentProvider = null;
        String paymentReference = null;
        BigDecimal amount = null;
        TransactionStatus status = null;
        LocalDateTime createdAt = null;

        userId = transactionUserId( transaction );
        orderId = transactionOrderId( transaction );
        id = transaction.getId();
        paymentProvider = transaction.getPaymentProvider();
        paymentReference = transaction.getPaymentReference();
        amount = transaction.getAmount();
        status = transaction.getStatus();
        createdAt = transaction.getCreatedAt();

        TransactionDto transactionDto = new TransactionDto( id, userId, orderId, paymentProvider, paymentReference, amount, status, createdAt );

        return transactionDto;
    }

    @Override
    public Transaction toEntity(TransactionDto transactionDto) {
        if ( transactionDto == null ) {
            return null;
        }

        Transaction.TransactionBuilder transaction = Transaction.builder();

        transaction.user( transactionDtoToUser( transactionDto ) );
        transaction.order( transactionDtoToOrder( transactionDto ) );
        transaction.id( transactionDto.id() );
        transaction.paymentProvider( transactionDto.paymentProvider() );
        transaction.paymentReference( transactionDto.paymentReference() );
        transaction.amount( transactionDto.amount() );
        transaction.status( transactionDto.status() );
        transaction.createdAt( transactionDto.createdAt() );

        return transaction.build();
    }

    private Long transactionUserId(Transaction transaction) {
        User user = transaction.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getId();
    }

    private Long transactionOrderId(Transaction transaction) {
        Order order = transaction.getOrder();
        if ( order == null ) {
            return null;
        }
        return order.getId();
    }

    protected User transactionDtoToUser(TransactionDto transactionDto) {
        if ( transactionDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( transactionDto.userId() );

        return user.build();
    }

    protected Order transactionDtoToOrder(TransactionDto transactionDto) {
        if ( transactionDto == null ) {
            return null;
        }

        Order.OrderBuilder order = Order.builder();

        order.id( transactionDto.orderId() );

        return order.build();
    }
}
