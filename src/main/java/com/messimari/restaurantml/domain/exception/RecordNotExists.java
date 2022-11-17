package com.messimari.restaurantml.domain.exception;

import lombok.Getter;

@Getter
public class RecordNotExists extends BusinessException {

    private Object[] objects;

    public RecordNotExists(Object[] objects) {
        super("Record_Not_Exists", objects);
        this.objects = objects;
    }
}
