package com.ecommerce.ecommerce.mapping;

import com.ecommerce.ecommerce.dto.CategoryDto;
import com.ecommerce.ecommerce.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface CategoryMapper {

    // Converts Category entity → CategoryDto
    // Maps the nested parentCategory object's ID (category.parentCategory.id) to a simple parentCategoryId field on the DTO
    // Useful for sending category data to the frontend without exposing the full parentCategory entity
    @Mapping(target = "parentCategoryId", source = "parentCategory.id")
    CategoryDto toDto(Category category);

    // Converts CategoryDto → Category entity
    // Maps the DTO's parentCategoryId back into the nested parentCategory object (category.parentCategory.id)
    // Allows reconstruction of the parent-child relationship when persisting or updating categories
    @Mapping(target = "parentCategory.id", source = "parentCategoryId")
    Category toEntity(CategoryDto categoryDto);

    // Converts a list of Category entities → CategoryDto objects
    List<CategoryDto> toDtoList(List<Category> categories);

    // Converts a list of CategoryDto objects → Category entities
    List<Category> toEntityList(List<CategoryDto> categoryDto);

}



