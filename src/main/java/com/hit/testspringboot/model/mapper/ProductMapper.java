package com.hit.testspringboot.model.mapper;

import com.hit.testspringboot.entity.Product;
import com.hit.testspringboot.model.dto.ProductDto;

public class ProductMapper {
    public static ProductDto toProductDto(Product product) {
        ProductDto tmp = new ProductDto();
        tmp.setId(product.getId());
        tmp.setCategoryId(product.getCategory().getId());
        tmp.setName(product.getName());
        tmp.setPrice(product.getPrice());
        tmp.setImage(product.getImage());
        tmp.setDescription(product.getDescription());
        tmp.setEvaluate(product.getEvaluate());
        tmp.setDiscount(product.getDiscount());
        tmp.setSold(product.getSold());
        return tmp;
    }
}
