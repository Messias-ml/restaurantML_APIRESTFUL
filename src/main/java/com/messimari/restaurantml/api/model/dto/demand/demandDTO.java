package com.messimari.restaurantml.api.model.dto.demand;

import com.messimari.restaurantml.domain.model.StatusDemand;
import com.messimari.restaurantml.domain.model.UserEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class demandDTO {

    private Long id;

    private BigDecimal totalValue;

    private UserEntity client;

    private StatusDemand status;

    private LocalDateTime confirmationDate;

    private String nameRestaurant;
}
