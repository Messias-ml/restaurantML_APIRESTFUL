package com.messimari.restaurantml.api.model.dto.itemDemand;

import com.messimari.restaurantml.api.model.dto.product.ProductNamePriceDTO;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemDemandResentationDTO {

    private Integer quantity;

    private BigDecimal totalPrice;

    private String observation;

    private ProductNamePriceDTO product;
}
