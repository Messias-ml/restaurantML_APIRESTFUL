package com.messimari.restaurantml.api.model.dto.product;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponseDTO {

    private String name;

    private BigDecimal price;

    private boolean active;
}
