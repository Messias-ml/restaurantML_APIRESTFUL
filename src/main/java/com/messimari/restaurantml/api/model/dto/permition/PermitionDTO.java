package com.messimari.restaurantml.api.model.dto.permition;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class PermitionDTO {

    @Size(min = 3)
    @NotBlank
    private String name;

    @Size(min = 3)
    @NotBlank
    private String description;
}
