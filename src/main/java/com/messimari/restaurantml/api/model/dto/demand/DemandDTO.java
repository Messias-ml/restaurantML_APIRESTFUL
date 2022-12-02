package com.messimari.restaurantml.api.model.dto.demand;

import com.messimari.restaurantml.api.model.dto.itemDemand.ItemDemandResentationDTO;
import com.messimari.restaurantml.api.model.dto.user.UserBasicDTO;
import com.messimari.restaurantml.domain.model.StatusDemand;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class DemandDTO {

    private Long id;

    private BigDecimal totalValue;

    private UserBasicDTO client;

    private List<ItemDemandResentationDTO> items;

    private StatusDemand status;

    private LocalDateTime confirmationDate;

    private String nameRestaurant;
}
