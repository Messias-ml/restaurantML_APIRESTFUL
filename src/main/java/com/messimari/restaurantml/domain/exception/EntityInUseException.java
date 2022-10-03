package com.messimari.restaurantml.domain.exception;

public class EntityInUseException extends RuntimeException{

    public EntityInUseException() {
        super("Entity_InUse");
    }
}
