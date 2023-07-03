package com.messimari.restaurantml.api.model.dto.city;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "City request model", description = "city representation model")
public class CityRequestDTO extends RepresentationModel<CityRequestDTO> {

    @ApiModelProperty(value = "name city", example = "Ubá", required = true)
    @NotBlank
    private String name;

    @NotNull
    private Long idState;
}
