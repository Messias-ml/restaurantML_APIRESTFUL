package com.messimari.restaurantml.api.model.dto.user;

import com.messimari.restaurantml.api.model.dto.group.GroupIdDTO;
import com.messimari.restaurantml.api.model.dto.group.ListGroupIdDTO;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class UserRegisterDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String password;

    @NotBlank
    @Email
    private String email;

    private List<GroupIdDTO> groups;
}
