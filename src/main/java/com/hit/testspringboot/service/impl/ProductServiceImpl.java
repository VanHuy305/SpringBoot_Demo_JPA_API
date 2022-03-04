package com.hit.testspringboot.service.impl;

import com.hit.testspringboot.entity.Category;
import com.hit.testspringboot.entity.Product;
import com.hit.testspringboot.exception.InternalServerException;
import com.hit.testspringboot.exception.NotFoundException;
import com.hit.testspringboot.model.dto.ProductDto;
import com.hit.testspringboot.model.mapper.ProductDtoMapper;
import com.hit.testspringboot.model.mapper.ProductMapper;
import com.hit.testspringboot.repository.CategoryRepository;
import com.hit.testspringboot.repository.ProductRepository;
import com.hit.testspringboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<ProductDto> getAllProduct() {
        List<Product> productList = productRepository.findAll();

        List<ProductDto> productDto = new ArrayList<>();
        for(Product product : productList) {
            productDto.add(ProductMapper.toProductDto(product));
        }
        return productDto;
    }

    @Override
    public ProductDto getProductById(int id) {
        Optional<Product> productList = productRepository.findById(id);
        if(productList.isEmpty()) {
            throw new NotFoundException("Not Found Product");
        }
        return ProductMapper.toProductDto(productList.get());
    }

    @Override
    public List<ProductDto> searchProduct(String keyword) {
        List<Product> productList = productRepository.findAll();

        List<ProductDto> productDto = new ArrayList<>();
        for(Product product : productList) {
            if(product.getName().contains(keyword)) {
                productDto.add(ProductMapper.toProductDto(product));
            }
        }
        return productDto;
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        Optional<Category> category = categoryRepository.findById(productDto.getCategoryId());
        Product product = ProductDtoMapper.toCategory(productDto, category.get());
        try {
            productRepository.save(product);
        } catch (Exception e) {
            throw new InternalServerException("Database error. Can't delete category");
        }
        return ProductMapper.toProductDto(product);
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Optional<Category> category = categoryRepository.findById(productDto.getCategoryId());
        Product product = ProductDtoMapper.toCategory(productDto, category.get());

        productRepository.save(product);
        return ProductMapper.toProductDto(product);
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
