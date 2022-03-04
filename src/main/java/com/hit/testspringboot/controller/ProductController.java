package com.hit.testspringboot.controller;

import com.hit.testspringboot.model.dto.ProductDto;
import com.hit.testspringboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<?> getAllProduct() {
        List<ProductDto> productList = productService.getAllProduct();
        return ResponseEntity.ok().body(productList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id) {
        ProductDto product = productService.getProductById(id);
        return ResponseEntity.ok().body(product);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchProduct(@RequestParam(name = "keyword", required = false, defaultValue = "") String name) {
        List<ProductDto> product = productService.searchProduct(name);
        return ResponseEntity.ok().body(product);
    }

    @PostMapping("")
    public ResponseEntity<?> createProduct(@RequestBody ProductDto product) {
        return ResponseEntity.ok().body(productService.createProduct(product));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody Map<Object, Object> fields, @PathVariable int id) {
        ProductDto product = productService.getProductById(id);
        fields.forEach((k, v) -> {
            Field field = ReflectionUtils.findField(ProductDto.class, (String) k);
            assert field != null;
            field.setAccessible(true);
            ReflectionUtils.setField(field, product, v);
        });
        return ResponseEntity.ok().body(productService.updateProduct(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
