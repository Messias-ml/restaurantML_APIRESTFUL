package com.messimari.restaurantml.core.validator.interfaces;

import com.messimari.restaurantml.core.validator.FileSizeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FileSizeValidator.class )
public @interface FileSize {

    String message() default "{error.file_size}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String max();
}
