package com.hit.testspringboot.service;

import com.hit.testspringboot.entity.Category;
import com.hit.testspringboot.model.dto.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface CategoryService {
    List<Category> getAllCategory();
    Category getCategoryById(int id);
    List<Category> searchCategory(String keyword);
    Category updateCategory(Map<Object, Object> fields, int id);
    Category createCategory(CategoryDto categoryDto);
    void deleteCategory(int id);
}
