package com.messimari.restaurantml.domain.exception;

import lombok.Getter;

@Getter
public class RecordNotExistsException extends BusinessException {

    private Object[] objects;

    public RecordNotExistsException(Object[] objects) {
        super("Record_Not_Exists", objects);
        this.objects = objects;
    }
}
