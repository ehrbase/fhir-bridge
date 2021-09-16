package org.ehrbase.fhirbridge.ehr.converter.specific.symptom;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition.NameDesSymptomsKrankheitsanzeichensDefiningCode;
import org.hl7.fhir.r4.model.Condition;

public interface SymptomConverter {

    default NameDesSymptomsKrankheitsanzeichensDefiningCode convertCode(Condition condition) {
        return condition.getCode()
                .getCoding()
                .stream()
                .filter(coding -> coding.getSystem().equals(CodeSystem.SNOMED.getUrl()))
                .findFirst()
                .map(coding -> NameDesSymptomsKrankheitsanzeichensDefiningCode.getCodesAsMap().get(coding.getCode()))
                .orElseThrow(() -> new ConversionException("Unbekanntes <unbekanntes Symptom>"));
    }
}
