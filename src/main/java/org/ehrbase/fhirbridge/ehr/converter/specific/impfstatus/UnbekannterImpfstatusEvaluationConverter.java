package org.ehrbase.fhirbridge.ehr.converter.specific.impfstatus;

import org.ehrbase.fhirbridge.ehr.converter.generic.EntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.impfstatuscomposition.definition.AussageUeberAbwesenheitDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.impfstatuscomposition.definition.UnbekannterImpfstatusEvaluation;
import org.hl7.fhir.r4.model.Immunization;

public class UnbekannterImpfstatusEvaluationConverter extends EntryEntityConverter<Immunization, UnbekannterImpfstatusEvaluation> {

    @Override
    protected UnbekannterImpfstatusEvaluation convertInternal(Immunization resource) {
        UnbekannterImpfstatusEvaluation unbekannterImpfstatusEvaluation = new UnbekannterImpfstatusEvaluation();
        unbekannterImpfstatusEvaluation.setAussageUeberAbwesenheitDefiningCode(mapAussageUeberAbwesenheitCode(resource));
        return unbekannterImpfstatusEvaluation;
    }

    private AussageUeberAbwesenheitDefiningCode mapAussageUeberAbwesenheitCode(Immunization resource) {

    }
}
