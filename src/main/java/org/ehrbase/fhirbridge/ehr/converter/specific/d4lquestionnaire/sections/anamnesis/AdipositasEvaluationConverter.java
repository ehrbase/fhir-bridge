package org.ehrbase.fhirbridge.ehr.converter.specific.d4lquestionnaire.sections.anamnesis;

import org.ehrbase.fhirbridge.ehr.converter.generic.QuestionnaireResponseItemToEntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.AdipositasEvaluation;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

public class AdipositasEvaluationConverter extends QuestionnaireResponseItemToEntryEntityConverter<AdipositasEvaluation> {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    protected AdipositasEvaluation convertInternal(QuestionnaireResponse.QuestionnaireResponseItemComponent questionnaireResponseItemComponent) {
            AdipositasEvaluation adipositasEvaluation = new AdipositasEvaluation();
            adipositasEvaluation.setNameDesProblemsDerDiagnoseValue("Adipositas");
            try {
                VorhandenerDefiningCodeConverter.setVorhandenerDefiningCode(getQuestionValueCodeToString((QuestionnaireResponse.QuestionnaireResponseItemComponent) questionnaireResponseItemComponent), adipositasEvaluation);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException noSuchMethodException) {
                log.error("Conversion error has occurred", noSuchMethodException);
            }
            return adipositasEvaluation;
        }
}
