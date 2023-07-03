package com.messimari.restaurantml.api.model.dto.state;

import com.messimari.restaurantml.api.model.dto.city.CityRequestDTO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class StateDTO extends RepresentationModel<StateDTO> {
    private Long id;

    private String name;
}
