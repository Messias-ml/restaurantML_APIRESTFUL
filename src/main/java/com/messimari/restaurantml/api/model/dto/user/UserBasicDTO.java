package com.messimari.restaurantml.api.model.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class UserBasicDTO {

    @JsonProperty(value = "nameClient")
    private String name;

    @Email
    private String email;
}
