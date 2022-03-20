package com.hit.testspringboot.service.impl;

import com.hit.testspringboot.entity.Category;
import com.hit.testspringboot.exception.InternalServerException;
import com.hit.testspringboot.exception.NotFoundException;
import com.hit.testspringboot.model.dto.CategoryDto;
import com.hit.testspringboot.model.mapper.CategoryDtoMapper;
import com.hit.testspringboot.repository.CategoryRepository;
import com.hit.testspringboot.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(int id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()) {
            throw new NotFoundException("Not Found Category");
        }
        return category.get();
    }

    @Override
    public List<Category> searchCategory(String keyword) {
        List<Category> categories = new ArrayList<>();
        for(Category category : categoryRepository.findAll()) {
            if(category.getName().contains(keyword)){
                categories.add(category);
            }
        }
        return categories;
    }

    @Override
    public Category updateCategory(Map<Object, Object> fields, int id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()) {
            throw new NotFoundException("Not Found Category");
        }
        fields.forEach((k, v) -> {
            Field field = ReflectionUtils.findField(Category.class, (String) k);
            assert field != null;
            field.setAccessible(true);
            ReflectionUtils.setField(field, category.get(), v);
        });
        try {
            return categoryRepository.save(category.get());
        } catch (Exception e) {
            throw new InternalServerException("Database error. Can't delete category");
        }
    }

    @Override
    public Category createCategory(CategoryDto categoryDto) {
        Category category = new Category();
        CategoryDtoMapper.toCategory(categoryDto, category);
        categoryRepository.save(category);
        return category;
    }

    @Override
    public void deleteCategory(int id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()) {
            throw new NotFoundException("Not Found Product");
        }
        try {
            categoryRepository.deleteById(id);
        } catch (Exception e) {
            throw new InternalServerException("Database error. Can't delete category");
        }
    }
}
