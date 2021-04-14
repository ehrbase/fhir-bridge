package org.ehrbase.fhirbridge.ehr.converter.specific.d4lquestionnaire.sections.symptoms;

import org.ehrbase.fhirbridge.ehr.converter.generic.QuestionnaireResponseItemToEntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.ProblemDiagnoseEvaluation;
import org.hl7.fhir.r4.model.QuestionnaireResponse;


public class ProblemDiagnoseEvaluationConverter extends QuestionnaireResponseItemToEntryEntityConverter<ProblemDiagnoseEvaluation> {
    ProblemDiagnoseEvaluation problemDiagnoseEvaluation = new ProblemDiagnoseEvaluation();

    @Override
    protected ProblemDiagnoseEvaluation convertInternal(QuestionnaireResponse.QuestionnaireResponseItemComponent questionnaireResponseItemComponent) {
        problemDiagnoseEvaluation.setNameDesProblemsDerDiagnoseValue("COVID-19 Fragebogen");
        return problemDiagnoseEvaluation;
    }

}
