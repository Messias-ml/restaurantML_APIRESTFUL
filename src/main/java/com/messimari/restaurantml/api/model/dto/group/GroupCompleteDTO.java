package com.messimari.restaurantml.api.model.dto.group;

import com.messimari.restaurantml.api.model.dto.permition.PermitionResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class GroupCompleteDTO {

    private String name;

    private List<PermitionResponseDTO> permissions;
}
