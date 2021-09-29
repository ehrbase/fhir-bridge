package org.ehrbase.fhirbridge.ehr.converter.specific.pulseoximetry;


import org.ehrbase.fhirbridge.ehr.opt.pulsoxymetriecomposition.definition.PulseOxtimetryDefiningCode;

import java.util.List;

import static org.ehrbase.fhirbridge.ehr.opt.pulsoxymetriecomposition.definition.PulseOxtimetryDefiningCode.LOINC_OXYGEN_SATURATION_IN_ARTERIAL_BLOOD;
import static org.ehrbase.fhirbridge.ehr.opt.pulsoxymetriecomposition.definition.PulseOxtimetryDefiningCode.LOINC_OXYGEN_SATURATION_IN_ARTERIAL_BLOOD_BY_PULSE_OXIMETRY;
import static org.ehrbase.fhirbridge.ehr.opt.pulsoxymetriecomposition.definition.PulseOxtimetryDefiningCode.SNOMED_PERIPHERAL_OXYGEN_SATURATION;

public enum PulseOxymetryCoding {
    CODING_1(List.of(LOINC_OXYGEN_SATURATION_IN_ARTERIAL_BLOOD)),
    CODING_2(List.of(LOINC_OXYGEN_SATURATION_IN_ARTERIAL_BLOOD, SNOMED_PERIPHERAL_OXYGEN_SATURATION)),
    CODING_3(List.of(LOINC_OXYGEN_SATURATION_IN_ARTERIAL_BLOOD, LOINC_OXYGEN_SATURATION_IN_ARTERIAL_BLOOD_BY_PULSE_OXIMETRY));

    private final List<PulseOxtimetryDefiningCode> code;

    PulseOxymetryCoding(List<PulseOxtimetryDefiningCode> code) {
        this.code = code;
    }

    public List<PulseOxtimetryDefiningCode> getCode() {
        return code;
    }
}
