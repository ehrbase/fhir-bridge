package org.ehrbase.fhirbridge.ehr.converter.specific.d4lquestionnaire.sections.anamnesis;

import org.ehrbase.fhirbridge.ehr.converter.generic.QuestionnaireResponseItemToEntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.DiabetesEvaluation;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

import java.lang.reflect.InvocationTargetException;

public class DiabetesEvaluationConverter extends QuestionnaireResponseItemToEntryEntityConverter<DiabetesEvaluation> {

    @Override
    protected DiabetesEvaluation convertInternal(QuestionnaireResponse.QuestionnaireResponseItemComponent questionnaireResponseItemComponent) {
        DiabetesEvaluation diabetesEvaluation = new DiabetesEvaluation();
        diabetesEvaluation.setNameDesProblemsDerDiagnoseValue("Diabetes");
        try {
            VorhandenerDefiningCodeConverter.setVorhandenerDefiningCode(getQuestionValueCodeToString(questionnaireResponseItemComponent), diabetesEvaluation);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException noSuchMethodException) {
            noSuchMethodException.printStackTrace();
        }
        return diabetesEvaluation;
    }
}
