package com.hit.testspringboot.controller;

import com.hit.testspringboot.model.dto.CategoryDto;
import com.hit.testspringboot.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<?> getAllCategory() {
        List<CategoryDto> categoryList = categoryService.getAllCategory();
        return ResponseEntity.ok().body(categoryList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable int id) {
        CategoryDto category = categoryService.getCategoryById(id);
        return ResponseEntity.ok().body(category);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchCategory(@RequestParam(name = "keyword", required = false, defaultValue = "") String name) {
        List<CategoryDto> category = categoryService.searchCategory(name);
        return ResponseEntity.ok().body(category);
    }

    @PostMapping("")
    public ResponseEntity<?> createCategory(@RequestBody CategoryDto category) {
        return ResponseEntity.ok().body(categoryService.createCategory(category));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateCategory(@RequestBody Map<Object, Object> fields, @PathVariable int id) {
        CategoryDto categoryDto = categoryService.getCategoryById(id);
        fields.forEach((k, v) -> {
            Field field = ReflectionUtils.findField(CategoryDto.class, (String) k);
            assert field != null;
            field.setAccessible(true);
            ReflectionUtils.setField(field, categoryDto, v);
        });
        return ResponseEntity.ok().body(categoryService.updateCategory(categoryDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }
}
