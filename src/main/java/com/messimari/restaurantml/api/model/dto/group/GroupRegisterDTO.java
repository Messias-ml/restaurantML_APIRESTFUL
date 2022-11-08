package com.messimari.restaurantml.api.model.dto.group;

import com.messimari.restaurantml.api.model.dto.permition.PermitionIdDTO;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class GroupRegisterDTO {

    @NotBlank
    private String name;

    private List<PermitionIdDTO> permissions;
}
