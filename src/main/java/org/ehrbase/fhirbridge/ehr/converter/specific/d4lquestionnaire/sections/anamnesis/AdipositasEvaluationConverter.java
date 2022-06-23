package org.ehrbase.fhirbridge.ehr.converter.specific.d4lquestionnaire.sections.anamnesis;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.fhirbridge.ehr.converter.generic.QuestionnaireResponseItemToEntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.AdipositasEvaluation;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

public class AdipositasEvaluationConverter extends QuestionnaireResponseItemToEntryEntityConverter<AdipositasEvaluation> {
    private static final Logger LOG = LoggerFactory.getLogger(AdipositasEvaluationConverter.class);

    @Override
    protected AdipositasEvaluation convertInternal(QuestionnaireResponse.QuestionnaireResponseItemComponent questionnaireResponseItemComponent) {
            AdipositasEvaluation adipositasEvaluation = new AdipositasEvaluation();
            adipositasEvaluation.setNameDesProblemsDerDiagnoseValue("Adipositas");
            try {
                VorhandenerDefiningCodeConverter.setVorhandenerDefiningCode(getQuestionValueCodeToString((QuestionnaireResponse.QuestionnaireResponseItemComponent) questionnaireResponseItemComponent), adipositasEvaluation);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException noSuchMethodException) {
                LOG.error("An Error occurred when injecting method, error: " + noSuchMethodException);
                throw new UnprocessableEntityException("The Defining Code could not be set.");
            }
            return adipositasEvaluation;
        }
}
