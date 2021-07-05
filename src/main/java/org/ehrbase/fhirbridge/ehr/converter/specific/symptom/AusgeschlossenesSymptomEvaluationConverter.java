package org.ehrbase.fhirbridge.ehr.converter.specific.symptom;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.EntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition.AussageUberDenAusschlussDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition.KrankheitsanzeichenCode;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition.AusgeschlossenesSymptomEvaluation;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Condition;

public class AusgeschlossenesSymptomEvaluationConverter extends EntryEntityConverter<Condition, AusgeschlossenesSymptomEvaluation> {

    @Override
    protected AusgeschlossenesSymptomEvaluation convertInternal(Condition resource) {
        AusgeschlossenesSymptomEvaluation ausgeschlossenesSymptom = new AusgeschlossenesSymptomEvaluation();
        setProblemDiagnose(resource, ausgeschlossenesSymptom);
        ausgeschlossenesSymptom.setAussageUeberDenAusschluss(AussageUberDenAusschlussDefiningCode.N410594000.toDvCodedText());
        return ausgeschlossenesSymptom;
    }

    private void setProblemDiagnose(Condition resource, AusgeschlossenesSymptomEvaluation ausgeschlossenesSymptom) {
            Coding coding = resource.getCode().getCoding().get(0);
            if (coding.getSystem().equals(CodeSystem.SNOMED.getUrl()) &&  KrankheitsanzeichenCode.getCodesAsMap().containsKey(coding.getCode())) {
                ausgeschlossenesSymptom.setProblemDiagnose(KrankheitsanzeichenCode.getCodesAsMap().get(coding.getCode()).toDvCodedText());
            }else{
                throw new ConversionException("Unbekanntes Diagnose/Problem.");
            }
    }
}
