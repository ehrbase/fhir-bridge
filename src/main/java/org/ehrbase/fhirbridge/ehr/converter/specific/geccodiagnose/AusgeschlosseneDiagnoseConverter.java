package org.ehrbase.fhirbridge.ehr.converter.specific.geccodiagnose;

import org.ehrbase.fhirbridge.ehr.converter.generic.EntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.AusgeschlosseneDiagnoseEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.AussageUeberDenAusschlussDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.NameDesProblemsDerDiagnoseDefiningCode;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Condition;

public class AusgeschlosseneDiagnoseConverter extends EntryEntityConverter<Condition, AusgeschlosseneDiagnoseEvaluation> {
    @Override
    protected AusgeschlosseneDiagnoseEvaluation convertInternal(Condition resource) {
        AusgeschlosseneDiagnoseEvaluation ausgeschlosseneDiagnose = new AusgeschlosseneDiagnoseEvaluation();
        ausgeschlosseneDiagnose.setAussageUeberDenAusschlussDefiningCode(AussageUeberDenAusschlussDefiningCode.KNOWN_ABSENT_QUALIFIER_VALUE);
        Coding problem = resource.getCode().getCoding().get(0);
        if (problem.getSystem().equals(CodeSystem.SNOMED.getUrl()) &&
                NameDesProblemsDerDiagnoseDefiningCode.getCodesAsMap().containsKey(problem.getCode())) {
            ausgeschlosseneDiagnose.setProblemDiagnoseDefiningCode(NameDesProblemsDerDiagnoseDefiningCode.getCodesAsMap().get(problem.getCode()));
        }
        return ausgeschlosseneDiagnose;
    }
}
