package com.messimari.restaurantml.api.model.dto.address;

import com.messimari.restaurantml.api.model.dto.city.CityDTO;
import lombok.Data;

@Data
public class AddressDTO {

    private String zipCode;

    private String patio;

    private Integer number;

    private String complement;

    private String neighborhood;

    private CityDTO city;
}
