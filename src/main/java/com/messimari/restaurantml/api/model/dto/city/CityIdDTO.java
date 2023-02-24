package com.messimari.restaurantml.api.model.dto.city;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("Id of city")
public class CityIdDTO {

    @NotNull
    @ApiModelProperty(example = "1")
    private Long id;
}
