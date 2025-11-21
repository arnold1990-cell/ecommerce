package com.ecommerce.ecommerce.mapping;

import com.ecommerce.ecommerce.dto.CategoryDto;
import com.ecommerce.ecommerce.model.Category;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-24T22:02:05+0200",
    comments = "version: 1.6.2, compiler: javac, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryDto toDto(Category category) {
        if ( category == null ) {
            return null;
        }

        Long parentCategoryId = null;
        Long id = null;
        String name = null;
        String description = null;
        List<CategoryDto> subcategories = null;
        String imageUrl = null;
        LocalDateTime createdAt = null;
        LocalDateTime updatedAt = null;

        parentCategoryId = categoryParentCategoryId( category );
        id = category.getId();
        name = category.getName();
        description = category.getDescription();
        subcategories = toDtoList( category.getSubcategories() );
        imageUrl = category.getImageUrl();
        createdAt = category.getCreatedAt();
        updatedAt = category.getUpdatedAt();

        CategoryDto categoryDto = new CategoryDto( id, name, description, parentCategoryId, subcategories, imageUrl, createdAt, updatedAt );

        return categoryDto;
    }

    @Override
    public Category toEntity(CategoryDto categoryDto) {
        if ( categoryDto == null ) {
            return null;
        }

        Category.CategoryBuilder category = Category.builder();

        category.parentCategory( categoryDtoToCategory( categoryDto ) );
        category.id( categoryDto.id() );
        category.name( categoryDto.name() );
        category.description( categoryDto.description() );
        category.subcategories( toEntityList( categoryDto.subcategories() ) );
        category.imageUrl( categoryDto.imageUrl() );
        category.createdAt( categoryDto.createdAt() );
        category.updatedAt( categoryDto.updatedAt() );

        return category.build();
    }

    @Override
    public List<CategoryDto> toDtoList(List<Category> categories) {
        if ( categories == null ) {
            return null;
        }

        List<CategoryDto> list = new ArrayList<CategoryDto>( categories.size() );
        for ( Category category : categories ) {
            list.add( toDto( category ) );
        }

        return list;
    }

    @Override
    public List<Category> toEntityList(List<CategoryDto> categoryDto) {
        if ( categoryDto == null ) {
            return null;
        }

        List<Category> list = new ArrayList<Category>( categoryDto.size() );
        for ( CategoryDto categoryDto1 : categoryDto ) {
            list.add( toEntity( categoryDto1 ) );
        }

        return list;
    }

    private Long categoryParentCategoryId(Category category) {
        Category parentCategory = category.getParentCategory();
        if ( parentCategory == null ) {
            return null;
        }
        return parentCategory.getId();
    }

    protected Category categoryDtoToCategory(CategoryDto categoryDto) {
        if ( categoryDto == null ) {
            return null;
        }

        Category.CategoryBuilder category = Category.builder();

        category.id( categoryDto.parentCategoryId() );

        return category.build();
    }
}
