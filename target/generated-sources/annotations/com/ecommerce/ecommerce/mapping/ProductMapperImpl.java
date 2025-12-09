package com.ecommerce.ecommerce.mapping;

import com.ecommerce.ecommerce.dto.ProductDto;
import com.ecommerce.ecommerce.model.Category;
import com.ecommerce.ecommerce.model.Product;
import com.ecommerce.ecommerce.model.User;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-25T12:00:01+0200",
    comments = "version: 1.6.2, compiler: javac, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDto toDto(Product product) {
        if ( product == null ) {
            return null;
        }

        Long sellerId = null;
        Long categoryId = null;
        Long id = null;
        String name = null;
        String description = null;
        BigDecimal price = null;
        BigDecimal discountPrice = null;
        Integer stock = null;
        String sku = null;
        String imageUrl = null;
        Double averageRating = null;
        LocalDateTime createdAt = null;
        LocalDateTime updatedAt = null;

        sellerId = productSellerId( product );
        categoryId = productCategoryId( product );
        id = product.getId();
        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        discountPrice = product.getDiscountPrice();
        stock = product.getStock();
        sku = product.getSku();
        imageUrl = product.getImageUrl();
        averageRating = product.getAverageRating();
        createdAt = product.getCreatedAt();
        updatedAt = product.getUpdatedAt();

        boolean isActive = false;

        ProductDto productDto = new ProductDto( id, sellerId, categoryId, name, description, price, discountPrice, stock, sku, imageUrl, averageRating, createdAt, updatedAt, isActive );

        return productDto;
    }

    @Override
    public Product toEntity(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.seller( productDtoToUser( productDto ) );
        product.category( productDtoToCategory( productDto ) );
        product.id( productDto.id() );
        product.name( productDto.name() );
        product.description( productDto.description() );
        product.price( productDto.price() );
        product.discountPrice( productDto.discountPrice() );
        product.stock( productDto.stock() );
        product.sku( productDto.sku() );
        product.imageUrl( productDto.imageUrl() );
        product.averageRating( productDto.averageRating() );
        product.createdAt( productDto.createdAt() );
        product.updatedAt( productDto.updatedAt() );
        product.isActive( productDto.isActive() );

        return product.build();
    }

    private Long productSellerId(Product product) {
        User seller = product.getSeller();
        if ( seller == null ) {
            return null;
        }
        return seller.getId();
    }

    private Long productCategoryId(Product product) {
        Category category = product.getCategory();
        if ( category == null ) {
            return null;
        }
        return category.getId();
    }

    protected User productDtoToUser(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( productDto.sellerId() );

        return user.build();
    }

    protected Category productDtoToCategory(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        Category.CategoryBuilder category = Category.builder();

        category.id( productDto.categoryId() );

        return category.build();
    }
}
