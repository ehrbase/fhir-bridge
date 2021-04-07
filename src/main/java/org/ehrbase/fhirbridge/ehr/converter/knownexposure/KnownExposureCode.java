package org.ehrbase.fhirbridge.ehr.converter.knownexposure;

import static org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem.SNOMED;
import static org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem.HL7_DATA_ABSENT_REASON;

public enum KnownExposureCode {

        KNOWN_EXPOSURE(SNOMED.getUrl(), "840546002"),
        NO_EXPOSURE(SNOMED.getUrl(), "373067005"),
        UNKNOWN_EXPOSURE(HL7_DATA_ABSENT_REASON.getUrl(), "unknown");

        private final String system;
        private final String code;

        KnownExposureCode(String system, String code) {
            this.code = code;
            this.system = system;
        }

        public String getCode() {
            return code;
        }

        public String getSystem() {
            return system;
        }
}
