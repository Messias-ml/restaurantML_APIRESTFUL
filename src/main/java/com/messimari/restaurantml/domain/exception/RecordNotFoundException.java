package com.messimari.restaurantml.domain.exception;

import lombok.Getter;

@Getter
public class RecordNotFoundException extends BusinessException{

    private Object[] objects;

    public RecordNotFoundException(Object[] objects) {
        super("Record_Not_Found", objects);
        this.objects = objects;
    }
}
