package org.ehrbase.fhirbridge.ehr.converter.specific.pulseoximetry;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

import java.util.List;

import static org.ehrbase.fhirbridge.ehr.converter.specific.pulseoximetry.PulseOxymetryCoding.CODING_1;
import static org.ehrbase.fhirbridge.ehr.converter.specific.pulseoximetry.PulseOxymetryCoding.CODING_2;
import static org.ehrbase.fhirbridge.ehr.converter.specific.pulseoximetry.PulseOxymetryCoding.CODING_3;

public class PulseOximetryCodeChecker {

    void checkIfPulseOximetry(Observation observation) {
        checkAmountOfCodes(observation);
    }

    private void checkAmountOfCodes(Observation observation) {
        if (observation.getCode().getCoding().size() == 1) {
            matchCoding1(observation.getCode().getCoding());
        } else if (observation.getCode().getCoding().size() == 2) {
            matchCoding2And3(observation.getCode().getCoding());
        } else {
            throw new ConversionException("Too many Codes, for oxygen saturation a maximum combination of two codes is supported");
        }
    }

    private void matchCoding1(List<Coding> codes) {
        String codeSystem = codes.get(0).getSystem();
        String code = codes.get(0).getCode();
        if (!(codeSystem.equals(CODING_1.getCode().get(0).getSystem()) && code.equals(CODING_1.getCode().get(0).getCode()))) {
            exceptionCode();
        }
    }

    private void matchCoding2And3(List<Coding> codes) {
        if (!matchCoding(codes, CODING_2) && !matchCoding(codes, CODING_3)) {
            exceptionCode();
        }

    }

    private boolean matchCoding(List<Coding> codingList, PulseOxymetryCoding coding) {
        int matchedCodes = 0;
        for (Coding code : codingList) {
            if (isCoding(code, coding)) {
                matchedCodes += 1;
            } else {
                return false;
            }
        }
        return matchedCodes == 2;
    }

    private boolean isCoding(Coding code, PulseOxymetryCoding coding) {
        return code.getSystem().equals(coding.getCode().get(0).getSystem()) && code.getCode().equals(coding.getCode().get(0).getCode()) ||
                code.getSystem().equals(coding.getCode().get(1).getSystem()) && code.getCode().equals(coding.getCode().get(1).getCode());
    }

    private void exceptionCode() {
        throw new ConversionException("The Code of code.coding is not supported for the Fhir-Bridge, or duplicated within the resource. If the LOINC-code 20564-1 or 2708-6 AND 20564-1 was entered, " +
                "the oxygen Saturation has to be send as part of a Blood gas panel. It can not be processed as a single resource in this cases.");

    }
}


