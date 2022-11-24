package com.messimari.restaurantml.api.model.dto.user;

import lombok.Data;

import java.util.List;

@Data
public class UserOwnerIdDTO {

    private List<UserIdDTO> owner;
}
