package com.messimari.restaurantml.domain.dto.restaurant;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.List;

@Data
public class RestaurantRequestDTO {

    @NotBlank
    private String name;

    @Min(0)
    private BigDecimal taxFrete;

    private Long idKitchen;

    private List<IdFormPayment> idFormPayment;
}
