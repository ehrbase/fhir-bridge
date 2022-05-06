package org.ehrbase.fhirbridge.ehr.converter.specific.d4lquestionnaire.sections.anamnesis;

import org.ehrbase.fhirbridge.ehr.converter.generic.QuestionnaireResponseItemToEntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.ChronischeLungenkrankheitEvaluation;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

public class ChronischeLungenkrankheitEvaluationConverter extends QuestionnaireResponseItemToEntryEntityConverter<ChronischeLungenkrankheitEvaluation> {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    protected ChronischeLungenkrankheitEvaluation convertInternal(QuestionnaireResponse.QuestionnaireResponseItemComponent questionnaireResponseItemComponent) {
        ChronischeLungenkrankheitEvaluation chronischeLungenkrankheitEvaluation = new ChronischeLungenkrankheitEvaluation();
        chronischeLungenkrankheitEvaluation.setNameDesProblemsDerDiagnoseValue("Chronische Lungenkrankheit");
        try {
            VorhandenerDefiningCodeConverter.setVorhandenerDefiningCode(getQuestionValueCodeToString(questionnaireResponseItemComponent), chronischeLungenkrankheitEvaluation);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException noSuchMethodException) {
            log.error("Conversion error has occurred", noSuchMethodException);
        }
        return chronischeLungenkrankheitEvaluation;
    }
}
