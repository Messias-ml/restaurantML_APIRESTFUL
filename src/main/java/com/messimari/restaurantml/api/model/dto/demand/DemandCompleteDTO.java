package com.messimari.restaurantml.api.model.dto.demand;

import com.messimari.restaurantml.api.model.dto.address.AddressRegisterDTO;
import com.messimari.restaurantml.api.model.dto.itemDemand.ItemDemandRequestDTO;
import com.messimari.restaurantml.api.model.dto.itemDemand.ItemDemandToDemandDTO;
import com.messimari.restaurantml.api.model.dto.restaurant.IdFormPayment;
import com.messimari.restaurantml.api.model.dto.restaurant.IdRestaurantDTO;
import com.messimari.restaurantml.api.model.dto.user.UserIdDTO;
import com.messimari.restaurantml.domain.model.StatusDemand;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
public class DemandCompleteDTO {

    @NotNull
    private BigDecimal taxFrete;

    @Valid
    private AddressRegisterDTO address;

    @NotNull
    private IdRestaurantDTO restaurant;

    @Valid
    private List<ItemDemandToDemandDTO> items;

    @NotNull
    private UserIdDTO client;

    @NotNull
    private IdFormPayment formPayment;

    @NotNull
    private StatusDemand status;
}
