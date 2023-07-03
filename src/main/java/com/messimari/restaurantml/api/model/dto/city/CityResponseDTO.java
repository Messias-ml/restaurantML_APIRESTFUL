package com.messimari.restaurantml.api.model.dto.city;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class CityResponseDTO extends RepresentationModel<CityRequestDTO> {

    private Long id;

    private String name;

    private String nameState;
}
