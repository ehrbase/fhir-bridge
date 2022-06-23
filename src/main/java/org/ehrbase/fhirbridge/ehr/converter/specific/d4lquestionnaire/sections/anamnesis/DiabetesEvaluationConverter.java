package org.ehrbase.fhirbridge.ehr.converter.specific.d4lquestionnaire.sections.anamnesis;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.fhirbridge.ehr.converter.generic.QuestionnaireResponseItemToEntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.DiabetesEvaluation;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

public class DiabetesEvaluationConverter extends QuestionnaireResponseItemToEntryEntityConverter<DiabetesEvaluation> {
    private static final Logger LOG = LoggerFactory.getLogger(DiabetesEvaluationConverter.class);

    @Override
    protected DiabetesEvaluation convertInternal(QuestionnaireResponse.QuestionnaireResponseItemComponent questionnaireResponseItemComponent) {
        DiabetesEvaluation diabetesEvaluation = new DiabetesEvaluation();
        diabetesEvaluation.setNameDesProblemsDerDiagnoseValue("Diabetes");
        try {
            VorhandenerDefiningCodeConverter.setVorhandenerDefiningCode(getQuestionValueCodeToString(questionnaireResponseItemComponent), diabetesEvaluation);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException noSuchMethodException) {
            LOG.error("An Error occurred when injecting method, error: " + noSuchMethodException);
            throw new UnprocessableEntityException("The Defining Code could not be set.");
        }
        return diabetesEvaluation;
    }
}
