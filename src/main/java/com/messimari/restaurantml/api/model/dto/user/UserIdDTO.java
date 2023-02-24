package com.messimari.restaurantml.api.model.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Id of user", description = "Id of client")
public class UserIdDTO {
    @ApiModelProperty(example = "1")
    private Long id;
}
