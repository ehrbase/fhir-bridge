package org.ehrbase.fhirbridge.exception.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ExceptionDto {
    int id;
    List<String> argumentsList;
}
