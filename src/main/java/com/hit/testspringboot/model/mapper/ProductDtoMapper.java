package com.hit.testspringboot.model.mapper;

import com.hit.testspringboot.entity.Category;
import com.hit.testspringboot.entity.Product;
import com.hit.testspringboot.model.dto.ProductDto;

public class ProductDtoMapper {
    public static Product toCategory(ProductDto productDto, Category category) {
        Product tmp = new Product();
        tmp.setId(productDto.getId());
        tmp.setCategory(category);
        tmp.setName(productDto.getName());
        tmp.setPrice(productDto.getPrice());
        tmp.setImage(productDto.getImage());
        tmp.setDescription(productDto.getDescription());
        tmp.setEvaluate(productDto.getEvaluate());
        tmp.setDiscount(productDto.getDiscount());
        tmp.setSold(productDto.getSold());
        return tmp;
    }
}
