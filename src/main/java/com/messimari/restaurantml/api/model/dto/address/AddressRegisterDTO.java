package com.messimari.restaurantml.api.model.dto.address;

import com.messimari.restaurantml.api.model.dto.city.CityIdDTO;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AddressRegisterDTO {

    @NotBlank
    private String zipCode;

    @NotBlank
    private String patio;

    @NotNull
    private Integer number;

    private String complement;

    @NotBlank
    private String neighborhood;

    @NotNull
    private CityIdDTO city;
}
