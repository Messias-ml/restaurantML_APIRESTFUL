package com.messimari.restaurantml.api.model.dto.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Id of product")
public class IdProductDTO {

    @ApiModelProperty(name = "id of product", example = "1")
    private Long id;
}
