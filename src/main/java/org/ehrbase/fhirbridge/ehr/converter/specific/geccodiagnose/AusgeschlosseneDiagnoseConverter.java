package org.ehrbase.fhirbridge.ehr.converter.specific.geccodiagnose;

import org.ehrbase.fhirbridge.ehr.converter.DvCodedTextParser;
import org.ehrbase.fhirbridge.ehr.converter.generic.EntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.AusgeschlosseneDiagnoseEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.AussageUeberDenAusschlussDefiningCode;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Condition;

public class AusgeschlosseneDiagnoseConverter extends EntryEntityConverter<Condition, AusgeschlosseneDiagnoseEvaluation> {
    @Override
    protected AusgeschlosseneDiagnoseEvaluation convertInternal(Condition resource) {
        AusgeschlosseneDiagnoseEvaluation ausgeschlosseneDiagnose = new AusgeschlosseneDiagnoseEvaluation();
        ausgeschlosseneDiagnose.setAussageUeberDenAusschlussDefiningCode(AussageUeberDenAusschlussDefiningCode.KNOWN_ABSENT_QUALIFIER_VALUE);
        for (Coding coding : resource.getCode().getCoding()) {
            if (coding.getSystem().equals(CodeSystem.SNOMED.getUrl())) {
                DvCodedTextParser.getInstance()
                        .parseFHIRCoding(coding)
                        .ifPresent(ausgeschlosseneDiagnose::setProblemDiagnose);
            }
        }
        return ausgeschlosseneDiagnose;
    }
}
