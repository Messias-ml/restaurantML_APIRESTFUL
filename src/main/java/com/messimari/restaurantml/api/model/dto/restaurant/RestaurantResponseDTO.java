package com.messimari.restaurantml.api.model.dto.restaurant;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RestaurantResponseDTO {

    private String name;

    private BigDecimal taxFrete;

    private String nameKitchen;
}
