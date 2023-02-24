package com.messimari.restaurantml.api.model.dto.restaurant;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Id of Restaurant")
public class IdRestaurantDTO {

    @ApiModelProperty(example = "1")
    private Long id;
}
