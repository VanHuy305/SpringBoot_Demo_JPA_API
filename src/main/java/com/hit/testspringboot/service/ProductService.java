package com.hit.testspringboot.service;

import com.hit.testspringboot.entity.Product;
import com.hit.testspringboot.model.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ProductService {
    List<Product> getAllProduct();
    Product getProductById(int id);
    List<Product> searchProduct(String keyword);
    Product updateProduct(Map<Object, Object> fields, int id);
    Product createProduct(ProductDto product);
    void deleteProduct(int id);
}
