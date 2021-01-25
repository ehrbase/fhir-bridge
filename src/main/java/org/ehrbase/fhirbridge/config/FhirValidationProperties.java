package org.ehrbase.fhirbridge.config;

import ca.uhn.fhir.validation.ResultSeverityEnum;
import org.ehrbase.fhirbridge.fhir.common.validation.TerminologyValidationMode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * {@link ConfigurationProperties ConfigurationProperties} to configure FHIR validation.
 */
@ConfigurationProperties(prefix = "fhir-bridge.fhir.validation")
public class FhirValidationProperties {

    private ResultSeverityEnum failedOnSeverity = ResultSeverityEnum.ERROR;

    private boolean anyExtensionsAllowed = true;

    private boolean errorForUnknownProfiles = false;

    @NestedConfigurationProperty
    private final Terminology terminology = new Terminology();

    public boolean isAnyExtensionsAllowed() {
        return anyExtensionsAllowed;
    }

    public void setAnyExtensionsAllowed(boolean anyExtensionsAllowed) {
        this.anyExtensionsAllowed = anyExtensionsAllowed;
    }

    public ResultSeverityEnum getFailedOnSeverity() {
        return failedOnSeverity;
    }

    public void setFailedOnSeverity(ResultSeverityEnum failedOnSeverity) {
        this.failedOnSeverity = failedOnSeverity;
    }

    public boolean isErrorForUnknownProfiles() {
        return errorForUnknownProfiles;
    }

    public void setErrorForUnknownProfiles(boolean errorForUnknownProfiles) {
        this.errorForUnknownProfiles = errorForUnknownProfiles;
    }

    public Terminology getTerminology() {
        return terminology;
    }

    public static class Terminology {

        private TerminologyValidationMode mode = TerminologyValidationMode.NONE;

        private String serverBaseUrl;

        public TerminologyValidationMode getMode() {
            return mode;
        }

        public void setMode(TerminologyValidationMode mode) {
            this.mode = mode;
        }

        public String getServerBaseUrl() {
            return serverBaseUrl;
        }

        public void setServerBaseUrl(String serverBaseUrl) {
            this.serverBaseUrl = serverBaseUrl;
        }
    }
}
