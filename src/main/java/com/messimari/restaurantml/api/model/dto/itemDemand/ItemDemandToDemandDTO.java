package com.messimari.restaurantml.api.model.dto.itemDemand;

import com.messimari.restaurantml.api.model.dto.product.IdProductDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ItemDemandToDemandDTO {

    @NotNull
    private Integer quantity;

    private String observation;

    @NotNull
    private IdProductDTO product;
}
