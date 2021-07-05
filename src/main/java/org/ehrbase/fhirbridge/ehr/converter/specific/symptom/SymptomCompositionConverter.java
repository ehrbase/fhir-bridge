package org.ehrbase.fhirbridge.ehr.converter.specific.symptom;


import org.ehrbase.fhirbridge.ehr.converter.generic.ConditionToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition.KategorieDefiningCodeSymptom;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.SymptomComposition;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition.StatusDefiningCode;

import org.hl7.fhir.r4.model.Condition;
import org.springframework.lang.NonNull;

public class SymptomCompositionConverter extends ConditionToCompositionConverter<SymptomComposition> {

    @Override
    public SymptomComposition convertInternal(@NonNull Condition resource) {
        SymptomComposition result = new SymptomComposition();
        if (resource.getVerificationStatus().isEmpty()) {
            result.setUnbekanntesSymptom(new UnbekanntesSymptomEvaluationConverter().convert(resource));
        } else if (resource.getVerificationStatus().getCoding().get(0).getCode().equals("confirmed")) {
            result.setVorliegendesSymptom(new VorliegendesSymptomObservationConverter().convert(resource));
        } else {
            result.setAusgeschlossenesSymptom(new AusgeschlossenesSymptomEvaluationConverter().convert(resource));
        }
        result.setStatusDefiningCode(StatusDefiningCode.FINAL);
        result.setKategorie(KategorieDefiningCodeSymptom.N753251.toDvCodedText());
        return result;
    }

}
