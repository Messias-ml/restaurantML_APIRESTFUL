package com.messimari.restaurantml.api.model.dto.photo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhotoResponseDTO {
    private String nameFile;

    private String description;

    private String contentType;

    private Long size;
}
