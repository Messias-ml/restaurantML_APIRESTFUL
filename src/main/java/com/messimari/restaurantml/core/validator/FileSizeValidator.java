package com.messimari.restaurantml.core.validator;

import com.messimari.restaurantml.core.validator.interfaces.FileSize;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FileSizeValidator implements ConstraintValidator<FileSize, MultipartFile> {

    private DataSize fileSize;

    @Override
    public void initialize(FileSize fileSize) {
        this.fileSize = DataSize.parse(fileSize.max());
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext constraintValidatorContext) {
        return value == null || value.getSize() <= fileSize.toBytes();
    }
}
