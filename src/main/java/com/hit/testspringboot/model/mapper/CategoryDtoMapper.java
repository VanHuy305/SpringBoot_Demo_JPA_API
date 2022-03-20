package com.hit.testspringboot.model.mapper;

import com.hit.testspringboot.entity.Category;
import com.hit.testspringboot.model.dto.CategoryDto;

public class CategoryDtoMapper {
    public static Category toCategory(CategoryDto categoryDto, Category category) {
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setImage(categoryDto.getImage());
        return category;
    }
}
