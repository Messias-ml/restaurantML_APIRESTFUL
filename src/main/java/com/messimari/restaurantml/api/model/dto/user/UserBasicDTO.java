package com.messimari.restaurantml.api.model.dto.user;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class UserBasicDTO {

    private String name;

    @Email
    private String email;
}
