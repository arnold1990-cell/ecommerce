package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.model.Product;
import com.ecommerce.ecommerce.model.ProductImage;
import com.ecommerce.ecommerce.repository.ProductImageRepository;
import com.ecommerce.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductImageService {

    private final ProductImageRepository productImageRepository;
    private final ProductRepository productRepository;

    // Add a new image to a product
    public ProductImage addImage(Long productId, String imageUrl) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        ProductImage image = ProductImage.builder()
                .product(product)
                .imageUrl(imageUrl)
                .build();

        return productImageRepository.save(image);
    }

    // Get all images for a product
    public List<ProductImage> getImagesByProduct(Long productId) {
        return productImageRepository.findByProductId(productId);
    }

    // Update image URL
    public ProductImage updateImage(Long id, String imageUrl) {
        ProductImage image = productImageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product image not found"));

        image.setImageUrl(imageUrl);
        return productImageRepository.save(image);
    }

    // Delete an image
    public void deleteImage(Long id) {
        if (!productImageRepository.existsById(id)) {
            throw new RuntimeException("Product image not found");
        }
        productImageRepository.deleteById(id);
    }
}
