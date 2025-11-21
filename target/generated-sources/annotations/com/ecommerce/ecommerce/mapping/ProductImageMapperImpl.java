package com.ecommerce.ecommerce.mapping;

import com.ecommerce.ecommerce.dto.ProductImageDto;
import com.ecommerce.ecommerce.model.Product;
import com.ecommerce.ecommerce.model.ProductImage;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-24T22:02:06+0200",
    comments = "version: 1.6.2, compiler: javac, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class ProductImageMapperImpl implements ProductImageMapper {

    @Override
    public ProductImageDto toDto(ProductImage productImage) {
        if ( productImage == null ) {
            return null;
        }

        Long productId = null;
        Long id = null;
        String imageUrl = null;
        LocalDateTime createdAt = null;
        LocalDateTime updatedAt = null;

        productId = productImageProductId( productImage );
        id = productImage.getId();
        imageUrl = productImage.getImageUrl();
        createdAt = productImage.getCreatedAt();
        updatedAt = productImage.getUpdatedAt();

        ProductImageDto productImageDto = new ProductImageDto( id, productId, imageUrl, createdAt, updatedAt );

        return productImageDto;
    }

    @Override
    public ProductImage toEntity(ProductImageDto productImageDto) {
        if ( productImageDto == null ) {
            return null;
        }

        ProductImage.ProductImageBuilder productImage = ProductImage.builder();

        productImage.product( productImageDtoToProduct( productImageDto ) );
        productImage.id( productImageDto.id() );
        productImage.imageUrl( productImageDto.imageUrl() );
        productImage.createdAt( productImageDto.createdAt() );
        productImage.updatedAt( productImageDto.updatedAt() );

        return productImage.build();
    }

    private Long productImageProductId(ProductImage productImage) {
        Product product = productImage.getProduct();
        if ( product == null ) {
            return null;
        }
        return product.getId();
    }

    protected Product productImageDtoToProduct(ProductImageDto productImageDto) {
        if ( productImageDto == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.id( productImageDto.productId() );

        return product.build();
    }
}
