package org.ehrbase.fhirbridge.fhir.common.validation;

public enum TerminologyValidationMode {

    /**
     * Validates content internally.
     */
    INTERNAL,

    /**
     * Validates content using a remote FHIR-based terminology server.
     */
    REMOTE,

    /**
     * Validates content using first the internal mechanism and then, the remote server (if needed).
     */
    MIXED,

    /**
     * No terminology validation performs.
     */
    NONE
}
