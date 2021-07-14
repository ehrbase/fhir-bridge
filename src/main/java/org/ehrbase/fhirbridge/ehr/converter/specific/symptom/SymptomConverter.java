package org.ehrbase.fhirbridge.ehr.converter.specific.symptom;

import com.nedap.archie.rm.datavalues.DvCodedText;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition.KrankheitsanzeichenCode;
import org.hl7.fhir.r4.model.Condition;

public interface SymptomConverter {

    default DvCodedText convertCode(Condition condition) {
        return condition.getCode()
                .getCoding()
                .stream()
                .filter(coding -> coding.getSystem().equals(CodeSystem.SNOMED.getUrl()))
                .findFirst()
                .map(coding -> KrankheitsanzeichenCode.getCodesAsMap().get(coding.getCode()))
                .orElseThrow(() -> new ConversionException("Unbekanntes <unbekanntes Symptom>"))
                .toDvCodedText();
    }
}
