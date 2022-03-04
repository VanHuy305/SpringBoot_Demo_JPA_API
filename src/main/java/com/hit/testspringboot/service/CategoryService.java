package com.hit.testspringboot.service;

import com.hit.testspringboot.model.dto.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<CategoryDto> getAllCategory();
    CategoryDto getCategoryById(int id);
    List<CategoryDto> searchCategory(String keyword);
    CategoryDto updateCategory(CategoryDto categoryDto);
    CategoryDto createCategory(CategoryDto categoryDto);
    void deleteCategory(int id);
}
