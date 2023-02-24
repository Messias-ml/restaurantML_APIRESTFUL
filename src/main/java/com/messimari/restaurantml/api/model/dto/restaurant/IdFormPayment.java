package com.messimari.restaurantml.api.model.dto.restaurant;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Id form payment")
public class IdFormPayment {

    @ApiModelProperty(example = "1")
    private Long id;
}
