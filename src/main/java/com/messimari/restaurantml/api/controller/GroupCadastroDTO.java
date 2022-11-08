package com.messimari.restaurantml.api.controller;

import com.messimari.restaurantml.api.model.dto.permition.PermitionIdDTO;
import lombok.Data;

import java.util.List;

@Data
public class GroupCadastroDTO {

    private String name;

    private List<PermitionIdDTO> permissions;
}
