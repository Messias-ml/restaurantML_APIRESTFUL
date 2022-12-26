package com.messimari.restaurantml.api.model.dto.demand;

import com.messimari.restaurantml.api.model.dto.user.UserBasicDTO;
import com.messimari.restaurantml.domain.model.StatusDemand;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DemandToRestaurantDTO {

    private BigDecimal totalValue;

    private BigDecimal taxFrete;

    private UserBasicDTO client;

    private StatusDemand status;

    private String nameRestaurant;
}
