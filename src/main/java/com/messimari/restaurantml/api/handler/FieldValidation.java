package com.messimari.restaurantml.api.handler;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FieldValidation {

    @ApiModelProperty(example = "name")
    private String name;

    @ApiModelProperty(example = "não deve estar em branco")
    private String reason;
}
