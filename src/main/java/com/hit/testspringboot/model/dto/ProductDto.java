package com.hit.testspringboot.model.dto;

import com.hit.testspringboot.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private int id;
    private int categoryId;
    private String name;
    private double price;
    private String image;
    private String description;
    private String evaluate;
    private int discount;
    private int sold;
}
