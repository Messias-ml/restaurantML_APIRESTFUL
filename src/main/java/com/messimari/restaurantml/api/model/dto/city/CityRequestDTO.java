package com.messimari.restaurantml.api.model.dto.city;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "City request model", description = "city representation model")
public class CityRequestDTO {

    @ApiModelProperty(value = "name city", example = "Ubá", required = true)
    @NotBlank
    private String name;

    @NotNull
    private Long idState;
}
