package com.hit.testspringboot.service;

import com.hit.testspringboot.model.dto.CategoryDto;
import com.hit.testspringboot.model.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<ProductDto> getAllProduct();
    ProductDto getProductById(int id);
    List<ProductDto> searchProduct(String keyword);
    ProductDto updateProduct(ProductDto productDto);
    ProductDto createProduct(ProductDto product);
    void deleteProduct(int id);
}
