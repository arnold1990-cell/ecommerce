package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.model.Category;
import com.ecommerce.ecommerce.model.Product;
import com.ecommerce.ecommerce.model.User;
import com.ecommerce.ecommerce.repository.CategoryRepository;
import com.ecommerce.ecommerce.repository.ProductRepository;
import com.ecommerce.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    // Create new product
    public Product createProduct(Product product, Long sellerId, Long categoryId) {

        User seller = userRepository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Seller not found"));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // attach references
        product.setSeller(seller);
        product.setCategory(category);

        // ensure SKU is unique
        if (productRepository.findBySku(product.getSku()).isPresent()) {
            throw new RuntimeException("SKU already exists");
        }

        return productRepository.save(product);
    }

    // Get single product
    public Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    // Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Update product
    public Product updateProduct(Long id, Product updated) {
        Product existing = getProduct(id);

        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        existing.setPrice(updated.getPrice());
        existing.setDiscountPrice(updated.getDiscountPrice());
        existing.setStock(updated.getStock());
        existing.setSku(updated.getSku());
        existing.setImageUrl(updated.getImageUrl());
        existing.setActive(updated.isActive());

        return productRepository.save(existing);
    }

    // Update stock only
    public Product updateStock(Long id, Integer newStock) {
        Product product = getProduct(id);

        if (newStock < 0) {
            throw new RuntimeException("Stock cannot be negative");
        }

        product.setStock(newStock);
        return productRepository.save(product);
    }

    // Activate / Deactivate product
    public Product changeActiveStatus(Long id, boolean active) {
        Product product = getProduct(id);
        product.setActive(active);
        return productRepository.save(product);
    }

    // Delete product
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found");
        }
        productRepository.deleteById(id);
    }
}
