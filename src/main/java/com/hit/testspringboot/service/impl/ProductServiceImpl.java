package com.hit.testspringboot.service.impl;

import com.hit.testspringboot.entity.Category;
import com.hit.testspringboot.entity.Product;
import com.hit.testspringboot.exception.InternalServerException;
import com.hit.testspringboot.exception.NotFoundException;
import com.hit.testspringboot.model.dto.ProductDto;
import com.hit.testspringboot.model.mapper.ProductDtoMapper;
import com.hit.testspringboot.repository.CategoryRepository;
import com.hit.testspringboot.repository.ProductRepository;
import com.hit.testspringboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(int id) {
        Optional<Product> productList = productRepository.findById(id);
        if(productList.isEmpty()) {
            throw new NotFoundException("Not Found Product");
        }
        return productList.get();
    }

    @Override
    public List<Product> searchProduct(String keyword) {
        List<Product> products = new ArrayList<>();

        for(Product product : productRepository.findAll()) {
            if(product.getName().contains(keyword)) {
                products.add(product);
            }
        }
        return products;
    }

    @Override
    public Product updateProduct(Map<Object, Object> fields, int id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()) {
            throw new NotFoundException("Not Found Product");
        }
        fields.forEach((k, v) -> {
            Field field = ReflectionUtils.findField(Product.class, (String) k);
            assert field != null;
            field.setAccessible(true);
            ReflectionUtils.setField(field, product.get(), v);
        });
        try {
            return productRepository.save(product.get());
        } catch (Exception e) {
            throw new InternalServerException("Database error. Can't delete category");
        }
    }

    @Override
    public Product createProduct(ProductDto productDto) {
        Optional<Category> category = categoryRepository.findById(productDto.getCategoryId());
        Product product = new Product();
        ProductDtoMapper.toProduct(productDto, product, category.get());
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(int id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()) {
            throw new NotFoundException("Not Found Product");
        }
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            throw new InternalServerException("Database error. Can't delete product");
        }
    }
}
