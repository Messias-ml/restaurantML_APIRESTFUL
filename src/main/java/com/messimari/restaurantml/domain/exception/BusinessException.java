package com.messimari.restaurantml.domain.exception;

public class BusinessException extends RuntimeException {

    private final transient Object object;

    public BusinessException(String message, Object object) {
        super(message);
        this.object = object;
    }
}
