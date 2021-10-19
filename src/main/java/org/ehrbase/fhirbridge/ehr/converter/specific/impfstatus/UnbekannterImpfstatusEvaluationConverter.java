package org.ehrbase.fhirbridge.ehr.converter.specific.impfstatus;

import org.ehrbase.fhirbridge.ehr.converter.CodingToDvCodedTextConverter;
import org.ehrbase.fhirbridge.ehr.converter.generic.EntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.impfstatuscomposition.definition.UnbekannterImpfstatusEvaluation;
import org.hl7.fhir.r4.model.Immunization;

public class UnbekannterImpfstatusEvaluationConverter extends EntryEntityConverter<Immunization, UnbekannterImpfstatusEvaluation> {

    @Override
    protected UnbekannterImpfstatusEvaluation convertInternal(Immunization resource) {
        UnbekannterImpfstatusEvaluation evaluation = new UnbekannterImpfstatusEvaluation();
        evaluation.setAussageUeberAbwesenheit(
                CodingToDvCodedTextConverter.getInstance()
                        .convert(resource.getVaccineCode().getCoding().get(0)));
        return evaluation;
    }

}
