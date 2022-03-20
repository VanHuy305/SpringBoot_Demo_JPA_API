package com.hit.testspringboot.controller;

import com.hit.testspringboot.model.dto.ProductDto;
import com.hit.testspringboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<?> getAllProduct() {
        return ResponseEntity.ok().body(productService.getAllProduct());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id) {
        return ResponseEntity.ok().body(productService.getProductById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchProduct(@RequestParam(name = "keyword", required = false, defaultValue = "") String name) {
        return ResponseEntity.ok().body(productService.searchProduct(name));
    }

    @PostMapping("")
    public ResponseEntity<?> createProduct(@RequestBody ProductDto product) {
        return ResponseEntity.ok().body(productService.createProduct(product));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody Map<Object, Object> fields, @PathVariable int id) {
        return ResponseEntity.ok().body(productService.updateProduct(fields, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
