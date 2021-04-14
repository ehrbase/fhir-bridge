package org.ehrbase.fhirbridge.ehr.converter.specific.historyoftravel;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToEvaluationConverter;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.ReisehistorieComposition;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.AussageUeberDenAusschlussDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.KeineReisehistorieEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.ProblemDiagnoseDefiningCode;
import org.hl7.fhir.r4.model.Observation;

public class KeineReisehistorieEvaluationConverter extends ObservationToEvaluationConverter<KeineReisehistorieEvaluation> {
    @Override
    protected KeineReisehistorieEvaluation convertInternal(Observation resource) {
        KeineReisehistorieEvaluation keineReisehistorieEvaluation = new KeineReisehistorieEvaluation();
        keineReisehistorieEvaluation.setProblemDiagnoseDefiningCode(ProblemDiagnoseDefiningCode.HISTORY_OF_TRAVEL);
        keineReisehistorieEvaluation.setAussageUeberDenAusschlussDefiningCode(AussageUeberDenAusschlussDefiningCode.NO_QUALIFIER_VALUE);
        return keineReisehistorieEvaluation;
    }
}
