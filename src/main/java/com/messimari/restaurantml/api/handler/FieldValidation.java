package com.messimari.restaurantml.api.handler;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FieldValidation {

    private String name;

    private String reason;
}
