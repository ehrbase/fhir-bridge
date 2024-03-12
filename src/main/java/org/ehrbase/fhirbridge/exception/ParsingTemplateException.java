package org.ehrbase.fhirbridge.exception;

import lombok.Getter;

@Getter
public class ParsingTemplateException extends RuntimeException {

  private final Class<?> entity;

  private final String paramValue;

  private final String message;

  public ParsingTemplateException(Class<?> entity, String paramValue, String message) {
    super(message);
    this.entity = entity;
    this.paramValue = paramValue;
    this.message = message;
  }

  public ParsingTemplateException() {
    message = null;
    paramValue = null;
    entity = null;
  }

    public ParsingTemplateException(Class<?> entity, String message) {
      super(message);
      this.entity = entity;
      this.paramValue = message;
      this.message = message;
    }
}
