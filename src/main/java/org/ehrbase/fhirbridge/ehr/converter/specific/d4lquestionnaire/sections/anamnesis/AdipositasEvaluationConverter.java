package org.ehrbase.fhirbridge.ehr.converter.specific.d4lquestionnaire.sections.anamnesis;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.converter.generic.QuestionnaireResponseItemToEntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.AdipositasEvaluation;
import org.hl7.fhir.r4.model.BackboneElement;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

import java.lang.reflect.InvocationTargetException;

public class AdipositasEvaluationConverter extends QuestionnaireResponseItemToEntryEntityConverter<AdipositasEvaluation> {


    @Override
    protected AdipositasEvaluation convertInternal(QuestionnaireResponse.QuestionnaireResponseItemComponent questionnaireResponseItemComponent) {
            AdipositasEvaluation adipositasEvaluation = new AdipositasEvaluation();
            adipositasEvaluation.setNameDesProblemsDerDiagnoseValue("Adipositas");
            try {
                VorhandenerDefiningCodeConverter.setVorhandenerDefiningCode(getQuestionValueCodeToString((QuestionnaireResponse.QuestionnaireResponseItemComponent) questionnaireResponseItemComponent), adipositasEvaluation);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException noSuchMethodException) {
                noSuchMethodException.printStackTrace();
            }
            return adipositasEvaluation;
        }
}
