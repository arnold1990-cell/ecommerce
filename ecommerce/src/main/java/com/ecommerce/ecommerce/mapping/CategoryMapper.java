package com.ecommerce.ecommerce.mapping;

import com.ecommerce.ecommerce.dto.CategoryDto;
import com.ecommerce.ecommerce.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",uses = {Category.class})
public interface CategoryMapper {

    @Mapping(target = "parentCategoryId",source = "parentCategory.id")
        CategoryDto toDto(Category category);
    @Mapping(target = "parentCategory.id",source = "parentCategoryId")
        Category toEntity(CategoryDto categoryDto);

        List<CategoryDto> toDtoList(List<Category> categories);

        List<Category> toEntityList(List<CategoryDto> categoryDto);

    }


