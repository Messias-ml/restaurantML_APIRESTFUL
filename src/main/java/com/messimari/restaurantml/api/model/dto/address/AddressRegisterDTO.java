package com.messimari.restaurantml.api.model.dto.address;

import com.messimari.restaurantml.api.model.dto.city.CityIdDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "Adress Register", description = "Address model DTO")
public class AddressRegisterDTO {

    @NotBlank
    @ApiModelProperty(example = "36507330")
    private String zipCode;

    @NotBlank
    @ApiModelProperty(example = "Rua José De Phillipo")
    private String patio;

    @NotNull
    @ApiModelProperty(example = "230")
    private Integer number;

    @ApiModelProperty(example = "ap 201")
    private String complement;

    @NotBlank
    @ApiModelProperty(example = "Santa Cruz")
    private String neighborhood;

    @NotNull
    private CityIdDTO city;
}
