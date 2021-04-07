package org.ehrbase.fhirbridge.ehr.converter.specific.pulseoximetry;

import static org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem.LOINC;
import static org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem.SNOMED;

public enum PulseOxtimetryCode {

    LOINC_OXYGEN_SATURATION_IN_ARTERIAL_BLOOD(LOINC.getUrl(), "2708-6"),
    LOINC_OXYGEN_SATURATION_IN_ARTERIAL_BLOOD_BY_PULSE_OXIMETRY(LOINC.getUrl(), "59408-5"),
    SNOMED_PERIPHERAL_OXYGEN_SATURATION(SNOMED.getUrl(), "431314004");

    private final String system;
    private final String code;

    PulseOxtimetryCode(String system, String code) {
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
