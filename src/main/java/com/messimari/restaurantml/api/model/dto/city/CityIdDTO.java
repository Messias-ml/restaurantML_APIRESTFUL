package com.messimari.restaurantml.api.model.dto.city;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CityIdDTO {

    @NotNull
    private Long id;
}
