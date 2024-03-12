package org.ehrbase.fhirbridge.exception;

import lombok.Getter;

@Getter
public class IODetailedException extends RuntimeException {

    private final Class<?> entity;

    private final String paramValue;

    private final String message;

    public IODetailedException(Class<?> entity, String paramValue, String message) {
        super(message);
        this.entity = entity;
        this.paramValue = paramValue;
        this.message = message;
    }

    public IODetailedException(Class<?> entity, String message) {
        super(message);
        this.entity = entity;
        this.paramValue = message;
        this.message = message;
    }

}
