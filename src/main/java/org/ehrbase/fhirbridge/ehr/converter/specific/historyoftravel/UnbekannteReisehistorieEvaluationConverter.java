package org.ehrbase.fhirbridge.ehr.converter.specific.historyoftravel;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToEvaluationConverter;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.AussageUeberDieFehlendeInformationDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.ProblemDiagnoseDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.UnbekannteReisehistorieEvaluation;
import org.hl7.fhir.r4.model.Observation;

public class UnbekannteReisehistorieEvaluationConverter extends ObservationToEvaluationConverter<UnbekannteReisehistorieEvaluation> {

    @Override
    protected UnbekannteReisehistorieEvaluation convertInternal(Observation resource) {
        UnbekannteReisehistorieEvaluation unbekannteReisehistorieEvaluation = new UnbekannteReisehistorieEvaluation();
        unbekannteReisehistorieEvaluation.setFehlendeInformationDefiningCode(ProblemDiagnoseDefiningCode.HISTORY_OF_TRAVEL);
        unbekannteReisehistorieEvaluation.setAussageUeberDieFehlendeInformationDefiningCode(AussageUeberDieFehlendeInformationDefiningCode.UNKNOWN_QUALIFIER_VALUE);
        return unbekannteReisehistorieEvaluation;
    }
}
