package com.hit.testspringboot.model.mapper;

import com.hit.testspringboot.entity.Category;
import com.hit.testspringboot.model.dto.CategoryDto;

public class CategoryMapper {
    public static CategoryDto toCategoryDto(Category category) {
        CategoryDto tmp = new CategoryDto();
        tmp.setId(category.getId());
        tmp.setName(category.getName());
        tmp.setDescription(category.getDescription());
        tmp.setImage(category.getImage());
        return tmp;
    }
}
