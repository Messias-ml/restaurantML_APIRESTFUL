package com.messimari.restaurantml.api.model.dto.group;

import com.messimari.restaurantml.api.model.dto.permition.PermitionDTO;
import lombok.Data;

import java.util.List;

@Data
public class GroupCompleteDTO {

    private String name;

    private List<PermitionDTO> permissions;
}
