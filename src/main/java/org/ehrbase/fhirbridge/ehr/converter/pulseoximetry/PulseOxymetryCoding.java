package org.ehrbase.fhirbridge.ehr.converter.pulseoximetry;


import java.util.ArrayList;
import static org.ehrbase.fhirbridge.ehr.converter.pulseoximetry.PulseOxtimetryCode.LOINC_OXYGEN_SATURATION_IN_ARTERIAL_BLOOD;
import static org.ehrbase.fhirbridge.ehr.converter.pulseoximetry.PulseOxtimetryCode.LOINC_OXYGEN_SATURATION_IN_ARTERIAL_BLOOD_BY_PULSE_OXIMETRY;
import static org.ehrbase.fhirbridge.ehr.converter.pulseoximetry.PulseOxtimetryCode.SNOMED_PERIPHERAL_OXYGEN_SATURATION;

public enum PulseOxymetryCoding {
    CODING_1(new ArrayList<>() {{
        add(LOINC_OXYGEN_SATURATION_IN_ARTERIAL_BLOOD);
    }}),


    CODING_2(new ArrayList<>() {{
        add(LOINC_OXYGEN_SATURATION_IN_ARTERIAL_BLOOD);
        add(SNOMED_PERIPHERAL_OXYGEN_SATURATION);
    }}),

    CODING_3(new ArrayList<>() {{
        add(LOINC_OXYGEN_SATURATION_IN_ARTERIAL_BLOOD);
        add(LOINC_OXYGEN_SATURATION_IN_ARTERIAL_BLOOD_BY_PULSE_OXIMETRY);
    }});


    private final ArrayList<PulseOxtimetryCode> code;

    PulseOxymetryCoding(ArrayList<PulseOxtimetryCode> code) {
        this.code = code;
    }

    public ArrayList<PulseOxtimetryCode> getCode() {
        return code;
    }
}
