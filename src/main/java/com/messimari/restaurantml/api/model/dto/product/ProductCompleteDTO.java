package com.messimari.restaurantml.api.model.dto.product;

import com.messimari.restaurantml.api.model.dto.restaurant.RestaurantResponseDTO;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductCompleteDTO {

    private String name;

    private BigDecimal price;

    private boolean active;

    private RestaurantResponseDTO restaurant;
}
