package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.model.ProductImage;
import com.ecommerce.ecommerce.service.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/product-images")
@RequiredArgsConstructor
public class ProductImageController {

    private final ProductImageService productImageService;

    // Add a new image to a product
    @PostMapping
    public ResponseEntity<ProductImage> addImage(
            @RequestParam Long productId,
            @RequestParam String imageUrl
    ) {
        return ResponseEntity.ok(productImageService.addImage(productId, imageUrl));
    }

    // Get all images for a product
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ProductImage>> getByProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(productImageService.getImagesByProduct(productId));
    }

    // Update an image
    @PutMapping("/{id}")
    public ResponseEntity<ProductImage> updateImage(
            @PathVariable Long id,
            @RequestParam String imageUrl
    ) {
        return ResponseEntity.ok(productImageService.updateImage(id, imageUrl));
    }

    // Delete an image
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable Long id) {
        productImageService.deleteImage(id);
        return ResponseEntity.ok("Product image deleted successfully");
    }
}
