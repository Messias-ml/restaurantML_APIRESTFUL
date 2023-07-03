package com.messimari.restaurantml.api.model.dto.user;

import com.messimari.restaurantml.api.model.dto.group.GroupNameDTO;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
public class UserCompleteDTO extends RepresentationModel<UserCompleteDTO> {

    private Long id;

    private String name;

    private String email;

    private List<GroupNameDTO> groups;
}
