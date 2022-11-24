package com.messimari.restaurantml.api.model.dto.restaurant;

import com.messimari.restaurantml.api.model.dto.address.AddressRegisterDTO;
import com.messimari.restaurantml.api.model.dto.user.UserIdDTO;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
public class RestaurantRequestDTO {

    @NotBlank
    private String name;

    @Min(0)
    private BigDecimal taxFrete;

    @NotNull
    private Long idKitchen;

    private List<IdFormPayment> idFormPayment;

    @Valid
    @NotNull
    private AddressRegisterDTO address;

    private List<UserIdDTO> owner;

}
