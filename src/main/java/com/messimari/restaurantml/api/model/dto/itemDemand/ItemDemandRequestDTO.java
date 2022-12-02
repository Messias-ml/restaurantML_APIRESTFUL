package com.messimari.restaurantml.api.model.dto.itemDemand;

import com.messimari.restaurantml.api.model.dto.demand.IdDemandDTO;
import com.messimari.restaurantml.api.model.dto.product.IdProductDTO;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemDemandRequestDTO {

    private Integer quantity;

    private BigDecimal unitaryPrice;

    private BigDecimal totalPrice;

    private String observation;

    private IdDemandDTO demand;

    private IdProductDTO product;
}
