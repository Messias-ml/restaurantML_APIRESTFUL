package com.messimari.restaurantml.api.handler;

import com.messimari.restaurantml.domain.exception.EntityInUseException;
import com.messimari.restaurantml.domain.exception.RecordNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<Problem> hendlerRecordNotFound(RecordNotFoundException recordNotFoundException) {
        String nameErro = "Registro não encontrado.";
        Problem problem = getProblem(recordNotFoundException.getMessage(), recordNotFoundException.getObjects(),
                nameErro, HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(problem);
    }

    @ExceptionHandler(EntityInUseException.class)
    public ResponseEntity<Problem> hendlerEntityInUse(EntityInUseException entityInUseException) {
        String nameErro = "Entidade em uso";
        Problem problem = getProblem(entityInUseException.getMessage(), null, nameErro, HttpStatus.CONFLICT.value());
        return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(problem);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ex.getRequestURL();
        String nameErro = "Registro não encontrado.";
        Problem problem = getProblem("UriNotExistsException", new Object[]{ex.getRequestURL()}, nameErro, status.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(problem);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String nameErro = "Infringiu validações basicas";
        List<FieldValidation> fieldValidations = new ArrayList<>();
        String message = "MethodArgumentNotValid";
        ex.getFieldErrors()
                .forEach(f -> {
                    fieldValidations.add(FieldValidation.builder()
                            .name(f.getField())
                            .reason(f.getDefaultMessage())
                            .build());
                });
        Problem problem = getProblem(message, null, nameErro, status.value());
        ProblemWithField problemWithField = new ProblemWithField(problem);
        problemWithField.setFieldValidation(fieldValidations);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(problemWithField);
    }
/*
    @ExceptionHandler(Exception.class)
    private ResponseEntity<Problem> handlerErroException(Exception ex) {
        String nameErro = "Erro inesperado";
        Integer status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        Problem problem = getProblem("ErrorException", null, nameErro, status);
        return ResponseEntity.status(status).body(problem);
    }*/

    private Problem getProblem(String message, Object[] objects, String nameErro, Integer status) {
        String detail = getMessage(objects, message.concat(".detail"));
        String messageUser = getMessage(objects, message.concat(".message_user"));
        return Problem.builder()
                .status(status)
                .title(nameErro)
                .detail(detail)
                .messageUser(messageUser)
                .build();
    }

    private ProblemWithField getProblemWithField(String message, Object[] objects, String nameErro, Integer status) {
        String detail = getMessage(objects, message.concat(".detail"));
        String messageUser = getMessage(objects, message.concat(".message_user"));
        ProblemWithField problemField = new ProblemWithField(getProblem(message, objects, nameErro, status));
        return problemField;
    }

    private String getMessage(Object[] objects, String message) {
        return messageSource.getMessage(message, objects, LocaleContextHolder.getLocale());
    }
}
