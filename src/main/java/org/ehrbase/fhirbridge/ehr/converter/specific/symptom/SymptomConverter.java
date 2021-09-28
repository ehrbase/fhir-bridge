package org.ehrbase.fhirbridge.ehr.converter.specific.symptom;

import com.nedap.archie.rm.datavalues.DvCodedText;
import org.ehrbase.fhirbridge.ehr.converter.parser.DvCodedTextParser;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
import org.hl7.fhir.r4.model.Condition;

import java.util.Optional;

public interface SymptomConverter {

    default Optional<DvCodedText> convertCode(Condition condition) {
        return condition.getCode()
                .getCoding()
                .stream()
                .filter(coding -> coding.getSystem().equals(CodeSystem.SNOMED.getUrl()))
                .findFirst()
                .map(DvCodedTextParser::parseFHIRCoding).get();
    }
}
