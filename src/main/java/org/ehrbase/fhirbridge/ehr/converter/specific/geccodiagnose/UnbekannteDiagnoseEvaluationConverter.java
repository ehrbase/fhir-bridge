package org.ehrbase.fhirbridge.ehr.converter.specific.geccodiagnose;

import org.ehrbase.fhirbridge.ehr.converter.generic.DvCodedTextParser;
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
        unbekannteDiagnose.setAussageUeberDieFehlendeInformation(DvCodedTextParser.parseDefiningCode(AussageUeberDieFehlendeInformationDefiningCode.UNKNOWN_QUALIFIER_VALUE));
        mapProblem(condition, unbekannteDiagnose);

        return unbekannteDiagnose;
    }

    private void mapProblem(Condition condition, UnbekannteDiagnoseEvaluation unbekannteDiagnose) {
        for(Coding coding : condition.getCode().getCoding()){
            if (coding.getSystem().equals(CodeSystem.SNOMED.getUrl()) &&
                    GeccoDiagnoseCodeDefiningCodeMaps.getProblemDiagnoseMap().containsKey(coding.getCode())) {
                unbekannteDiagnose.setUnbekannteDiagnose(DvCodedTextParser.parseDefiningCode(GeccoDiagnoseCodeDefiningCodeMaps.getProblemDiagnoseMap().get(coding.getCode())));
            }
        }
    }
}
