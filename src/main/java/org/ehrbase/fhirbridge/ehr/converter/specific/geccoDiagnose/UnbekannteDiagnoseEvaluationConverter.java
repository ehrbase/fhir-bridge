package org.ehrbase.fhirbridge.ehr.converter.specific.geccoDiagnose;

import org.ehrbase.fhirbridge.ehr.converter.generic.EntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.AussageUeberDieFehlendeInformationDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.UnbekannteDiagnoseEvaluation;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Condition;

public class UnbekannteDiagnoseEvaluationConverter extends EntryEntityConverter<Condition, UnbekannteDiagnoseEvaluation> {

    @Override
    protected UnbekannteDiagnoseEvaluation convertInternal(Condition condition) {
        UnbekannteDiagnoseEvaluation unbekannteDiagnose = new UnbekannteDiagnoseEvaluation();
        unbekannteDiagnose.setAussageUeberDieFehlendeInformationDefiningCode(AussageUeberDieFehlendeInformationDefiningCode.UNKNOWN_QUALIFIER_VALUE);
        Coding problem = condition.getCode().getCoding().get(0);
        if (problem.getSystem().equals(CodeSystem.SNOMED.getUrl()) &&
                GeccoDiagnoseCodeDefiningCodeMaps.getProblemDiagnoseMap().containsKey(problem.getCode())) {
            unbekannteDiagnose.setUnbekannteDiagnoseDefiningCode(GeccoDiagnoseCodeDefiningCodeMaps.getProblemDiagnoseMap().get(problem.getCode()));
        }
        return unbekannteDiagnose;
    }
}
