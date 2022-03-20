package com.hit.testspringboot.controller;

import com.hit.testspringboot.model.dto.CategoryDto;
import com.hit.testspringboot.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<?> getAllCategory() {
        return ResponseEntity.ok().body(categoryService.getAllCategory());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable int id) {
        return ResponseEntity.ok().body(categoryService.getCategoryById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchCategory(@RequestParam(name = "keyword", required = false, defaultValue = "") String name) {
        return ResponseEntity.ok().body(categoryService.searchCategory(name));
    }

    @PostMapping("")
    public ResponseEntity<?> createCategory(@RequestBody CategoryDto category) {
        return ResponseEntity.ok().body(categoryService.createCategory(category));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateCategory(@RequestBody Map<Object, Object> fields, @PathVariable int id) {
        return ResponseEntity.ok().body(categoryService.updateCategory(fields, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }
}
