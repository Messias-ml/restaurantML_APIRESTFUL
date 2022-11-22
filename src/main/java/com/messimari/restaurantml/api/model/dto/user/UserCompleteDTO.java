package com.messimari.restaurantml.api.model.dto.user;

import com.messimari.restaurantml.api.model.dto.group.GroupNameDTO;
import lombok.Data;

import java.util.List;

@Data
public class UserCompleteDTO {

    private Long id;

    private String name;

    private String email;

    private List<GroupNameDTO> groups;
}
