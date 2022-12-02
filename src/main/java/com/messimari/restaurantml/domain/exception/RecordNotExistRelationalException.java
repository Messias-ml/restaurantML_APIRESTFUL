package com.messimari.restaurantml.domain.exception;

import lombok.Getter;

@Getter
public class RecordNotExistRelationalException extends BusinessException {

    private Object[] objects;

    public RecordNotExistRelationalException(Object[] objects) {
        super("Record_Not_Exist_Relational", objects);
        this.objects = objects;
    }
}
