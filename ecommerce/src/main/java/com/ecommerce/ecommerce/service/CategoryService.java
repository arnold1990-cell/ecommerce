package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.model.Category;
import com.ecommerce.ecommerce.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    /**
     * CREATE a new category
     */
    public Category createCategory(Category category) {
        if (categoryRepository.findByName(category.getName()).isPresent()) {
            throw new RuntimeException("Category name must be unique");
        }
        return categoryRepository.save(category);
    }

    /**
     * GET ALL categories
     */
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    /**
     * GET category by ID
     */
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    /**
     * UPDATE category
     */
    public Category updateCategory(Long id, Category newDetails) {
        Category category = getCategoryById(id);

        if (!category.getName().equals(newDetails.getName()) &&
                categoryRepository.findByName(newDetails.getName()).isPresent()) {
            throw new RuntimeException("Category name must be unique");
        }

        category.setName(newDetails.getName());
        category.setDescription(newDetails.getDescription());
        category.setImageUrl(newDetails.getImageUrl());
        category.setParentCategory(newDetails.getParentCategory());

        return categoryRepository.save(category);
    }

    /**
     * DELETE category
     */
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
