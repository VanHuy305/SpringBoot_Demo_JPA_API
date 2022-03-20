package com.hit.testspringboot.model.mapper;

import com.hit.testspringboot.entity.Category;
import com.hit.testspringboot.entity.Product;
import com.hit.testspringboot.model.dto.ProductDto;

public class ProductDtoMapper {
    public static Product toProduct(ProductDto productDto, Product product, Category category) {
        product.setCategory(category);
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setImage(productDto.getImage());
        product.setDescription(productDto.getDescription());
        product.setEvaluate(productDto.getEvaluate());
        product.setDiscount(productDto.getDiscount());
        product.setSold(productDto.getSold());
        return product;
    }
}
