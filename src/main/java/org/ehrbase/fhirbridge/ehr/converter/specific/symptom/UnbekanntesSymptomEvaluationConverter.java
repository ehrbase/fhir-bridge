package org.ehrbase.fhirbridge.ehr.converter.specific.symptom;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.EntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
import org.ehrbase.fhirbridge.ehr.converter.specific.symptom.codes.DefiningCode;
import org.ehrbase.fhirbridge.ehr.converter.specific.symptom.codes.KrankheitsanzeichenCode;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition.UnbekanntesSymptomAussageUeberDieFehlendeInformationElement;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition.UnbekanntesSymptomEvaluation;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Condition;

import java.util.ArrayList;

public class UnbekanntesSymptomEvaluationConverter extends EntryEntityConverter<Condition, UnbekanntesSymptomEvaluation> {


    @Override
    protected UnbekanntesSymptomEvaluation convertInternal(Condition resource) {
        UnbekanntesSymptomEvaluation unbekanntesSymptom = new UnbekanntesSymptomEvaluation();
        setUnbekanntesSymptom(resource, unbekanntesSymptom);
        UnbekanntesSymptomAussageUeberDieFehlendeInformationElement aussageUberDieFehlendeInformationElement = new UnbekanntesSymptomAussageUeberDieFehlendeInformationElement();
        aussageUberDieFehlendeInformationElement.setValue(DefiningCode.N261665006.toDvCodedText());
        if (unbekanntesSymptom.getAussageUeberDieFehlendeInformation() == null) {
            unbekanntesSymptom.setAussageUeberDieFehlendeInformation(new ArrayList<>());
        }
        unbekanntesSymptom.getAussageUeberDieFehlendeInformation().add(aussageUberDieFehlendeInformationElement);
        return unbekanntesSymptom;

    }

    private void setUnbekanntesSymptom(Condition resource, UnbekanntesSymptomEvaluation unbekanntesSymptom) {
        Coding coding = resource.getCode().getCoding().get(0);
        if (coding.getSystem().equals(CodeSystem.SNOMED.getUrl()) &&  KrankheitsanzeichenCode.getCodesAsMap().containsKey(coding.getCode())) {
            unbekanntesSymptom.setUnbekanntesSymptom(KrankheitsanzeichenCode.getCodesAsMap().get(coding.getCode()).toDvCodedText());
        }else{
            throw new ConversionException("Unbekanntes <unbekanntes Symptom>");
        }
    }
}
