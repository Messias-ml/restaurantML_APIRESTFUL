package com.messimari.restaurantml.api.model.dto;

import com.messimari.restaurantml.core.validator.interfaces.FileContentType;
import com.messimari.restaurantml.core.validator.interfaces.FileSize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PhotoDTO {

    @NotNull
    @FileSize(max = "10KB")
    @FileContentType(allowed = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
    private MultipartFile photo;

    @NotBlank
    private String description;
}
