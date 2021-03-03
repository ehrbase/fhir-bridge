package org.ehrbase.fhirbridge.ehr.converter.pulseoximetry;


import java.util.ArrayList;
import java.util.List;

import static org.ehrbase.fhirbridge.ehr.converter.pulseoximetry.PulseOxtimetryCode.LOINC_OXYGEN_SATURATION_IN_ARTERIAL_BLOOD;
import static org.ehrbase.fhirbridge.ehr.converter.pulseoximetry.PulseOxtimetryCode.LOINC_OXYGEN_SATURATION_IN_ARTERIAL_BLOOD_BY_PULSE_OXIMETRY;
import static org.ehrbase.fhirbridge.ehr.converter.pulseoximetry.PulseOxtimetryCode.SNOMED_PERIPHERAL_OXYGEN_SATURATION;

public enum PulseOxymetryCoding {
    CODING_1(List.of(LOINC_OXYGEN_SATURATION_IN_ARTERIAL_BLOOD)),
    CODING_2(List.of(LOINC_OXYGEN_SATURATION_IN_ARTERIAL_BLOOD, SNOMED_PERIPHERAL_OXYGEN_SATURATION)),
    CODING_3(List.of(LOINC_OXYGEN_SATURATION_IN_ARTERIAL_BLOOD, LOINC_OXYGEN_SATURATION_IN_ARTERIAL_BLOOD_BY_PULSE_OXIMETRY));
    
    private final List<PulseOxtimetryCode> code;

    PulseOxymetryCoding(List<PulseOxtimetryCode> code) {
        this.code = code;
    }

    public List<PulseOxtimetryCode> getCode() {
        return code;
    }
}
