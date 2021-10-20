package org.ehrbase.fhirbridge.service;

public class TerminologyServiceException extends RuntimeException {

    public TerminologyServiceException(String message) {
        super(message);
    }

    public TerminologyServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
