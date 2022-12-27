package com.messimari.restaurantml.core.validator;

import com.messimari.restaurantml.core.validator.interfaces.FileContentType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class FileContentTypeValidator implements ConstraintValidator<FileContentType, MultipartFile> {

    private List<String> typeAllowed;

    @Override
    public void initialize(FileContentType constraintAnnotation) {
        typeAllowed = List.of(constraintAnnotation.allowed());
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext constraintValidatorContext) {
        return this.typeAllowed.contains(file.getContentType());
    }
}
