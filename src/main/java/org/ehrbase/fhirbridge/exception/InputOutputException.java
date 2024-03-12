package org.ehrbase.fhirbridge.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class InputOutputException extends RuntimeException  {

  private final Class<?> entity;
  private final String entityId;
}
