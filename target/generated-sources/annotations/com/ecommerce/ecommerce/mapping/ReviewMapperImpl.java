package com.ecommerce.ecommerce.mapping;

import com.ecommerce.ecommerce.dto.ReviewDto;
import com.ecommerce.ecommerce.model.Product;
import com.ecommerce.ecommerce.model.Review;
import com.ecommerce.ecommerce.model.User;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-25T12:00:01+0200",
    comments = "version: 1.6.2, compiler: javac, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class ReviewMapperImpl implements ReviewMapper {

    @Override
    public ReviewDto toDto(Review review) {
        if ( review == null ) {
            return null;
        }

        Long productId = null;
        Long userId = null;
        Long id = null;
        Integer rating = null;
        String comment = null;
        LocalDateTime createdAt = null;

        productId = reviewProductId( review );
        userId = reviewUserId( review );
        id = review.getId();
        rating = review.getRating();
        comment = review.getComment();
        createdAt = review.getCreatedAt();

        ReviewDto reviewDto = new ReviewDto( id, productId, userId, rating, comment, createdAt );

        return reviewDto;
    }

    @Override
    public Review toEntity(ReviewDto reviewDto) {
        if ( reviewDto == null ) {
            return null;
        }

        Review.ReviewBuilder review = Review.builder();

        review.product( reviewDtoToProduct( reviewDto ) );
        review.user( reviewDtoToUser( reviewDto ) );
        review.id( reviewDto.id() );
        review.rating( reviewDto.rating() );
        review.comment( reviewDto.comment() );
        review.createdAt( reviewDto.createdAt() );

        return review.build();
    }

    private Long reviewProductId(Review review) {
        Product product = review.getProduct();
        if ( product == null ) {
            return null;
        }
        return product.getId();
    }

    private Long reviewUserId(Review review) {
        User user = review.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getId();
    }

    protected Product reviewDtoToProduct(ReviewDto reviewDto) {
        if ( reviewDto == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.id( reviewDto.productId() );

        return product.build();
    }

    protected User reviewDtoToUser(ReviewDto reviewDto) {
        if ( reviewDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( reviewDto.userId() );

        return user.build();
    }
}
