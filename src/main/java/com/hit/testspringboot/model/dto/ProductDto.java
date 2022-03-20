package com.hit.testspringboot.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String name;
    private double price;
    private String image;
    private String description;
    private String evaluate;
    private int discount;
    private int sold;
    private int categoryId;
}
