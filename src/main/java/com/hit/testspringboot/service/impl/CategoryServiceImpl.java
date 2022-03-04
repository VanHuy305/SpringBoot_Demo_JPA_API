package com.hit.testspringboot.service.impl;

import com.hit.testspringboot.entity.Category;
import com.hit.testspringboot.exception.InternalServerException;
import com.hit.testspringboot.exception.NotFoundException;
import com.hit.testspringboot.model.dto.CategoryDto;
import com.hit.testspringboot.model.mapper.CategoryMapper;
import com.hit.testspringboot.repository.CategoryRepository;
import com.hit.testspringboot.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> getAllCategory() {
        List<CategoryDto> categoryDto = new ArrayList<>();

        List<Category> categoryList = categoryRepository.findAll();
        for(Category category : categoryList) {
            categoryDto.add(CategoryMapper.toCategoryDto(category));
        }
        return categoryDto;
    }

    @Override
    public CategoryDto getCategoryById(int id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()) {
            throw new NotFoundException("Not Found Category");
        }
        return CategoryMapper.toCategoryDto(category.get());
    }

    @Override
    public List<CategoryDto> searchCategory(String keyword) {
        List<CategoryDto> categoryDto = new ArrayList<>();

        List<Category> categoryList = categoryRepository.findAll();
        for(Category category : categoryList) {
            if(category.getName().contains(keyword)){
                categoryDto.add(CategoryMapper.toCategoryDto(category));
            }
        }
        return categoryDto;
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) {
        Category c = new Category();
        c.setId(categoryDto.getId());
        c.setName(categoryDto.getName());
        c.setDescription(categoryDto.getDescription());
        c.setImage(categoryDto.getImage());

        try {
            categoryRepository.save(c);
        } catch (Exception e) {
            throw new InternalServerException("Database error. Can't delete category");
        }
        return CategoryMapper.toCategoryDto(c);
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category c = new Category();
        c.setName(categoryDto.getName());
        c.setDescription(categoryDto.getDescription());
        c.setImage(categoryDto.getImage());

        categoryRepository.save(c);
        System.out.println("Id category: " + c.getId());
        return CategoryMapper.toCategoryDto(c);
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
