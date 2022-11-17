package com.messimari.restaurantml.api.model.dto.product;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class ProductRequestDTO {

    @NotBlank
    @Size(min = 3)
    private String name;

    @NotBlank
    @Size(min = 3)
    private String description;

    @NotNull
    @Min(0)
    private BigDecimal price;

    @NotNull
    private Long idRestaurant;
}
