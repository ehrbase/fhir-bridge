package org.ehrbase.fhirbridge.ehr.converter.specific.d4lquestionnaire.sections.anamnesis;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.fhirbridge.ehr.converter.generic.QuestionnaireResponseItemToEntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.ChronischeLungenkrankheitEvaluation;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

public class ChronischeLungenkrankheitEvaluationConverter extends QuestionnaireResponseItemToEntryEntityConverter<ChronischeLungenkrankheitEvaluation> {
    private static final Logger LOG = LoggerFactory.getLogger(ChronischeLungenkrankheitEvaluationConverter.class);

    @Override
    protected ChronischeLungenkrankheitEvaluation convertInternal(QuestionnaireResponse.QuestionnaireResponseItemComponent questionnaireResponseItemComponent) {
        ChronischeLungenkrankheitEvaluation chronischeLungenkrankheitEvaluation = new ChronischeLungenkrankheitEvaluation();
        chronischeLungenkrankheitEvaluation.setNameDesProblemsDerDiagnoseValue("Chronische Lungenkrankheit");
        try {
            VorhandenerDefiningCodeConverter.setVorhandenerDefiningCode(getQuestionValueCodeToString(questionnaireResponseItemComponent), chronischeLungenkrankheitEvaluation);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException noSuchMethodException) {
            LOG.error("An Error occurred when injecting method, error: " + noSuchMethodException);
            throw new UnprocessableEntityException("The Defining Code could not be set.");
        }
        return chronischeLungenkrankheitEvaluation;
    }
}
