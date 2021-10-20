package org.ehrbase.fhirbridge.ehr.converter.specific.impfstatus;

import org.ehrbase.fhirbridge.ehr.converter.DvCodedTextParser;
import org.ehrbase.fhirbridge.ehr.converter.generic.EntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.impfstatuscomposition.definition.UnbekannterImpfstatusEvaluation;
import org.hl7.fhir.r4.model.Immunization;

public class UnbekannterImpfstatusEvaluationConverter extends EntryEntityConverter<Immunization, UnbekannterImpfstatusEvaluation> {

    @Override
    protected UnbekannterImpfstatusEvaluation convertInternal(Immunization immunization) {
        UnbekannterImpfstatusEvaluation unbekannterImpfstatusEvaluation = new UnbekannterImpfstatusEvaluation();
        DvCodedTextParser.getInstance()
                .parseFHIRCoding(immunization.getVaccineCode().getCoding().get(0))
                .ifPresent(unbekannterImpfstatusEvaluation::setAussageUeberAbwesenheit);
        return unbekannterImpfstatusEvaluation;
    }

}
