package com.messimari.restaurantml.api.model.dto.product;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductNamePriceDTO {

    private String nameProduct;

    private BigDecimal price;
}
