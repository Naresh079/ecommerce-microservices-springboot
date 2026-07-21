package com.learner.productservice.dto;

import java.math.BigDecimal;

import org.antlr.v4.runtime.misc.NotNull;


import lombok.Data;

@Data
public class ProductRequestDTO {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private String category;
    private String imageUrl;
}
