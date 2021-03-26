package org.ehrbase.fhirbridge.ehr.converter.specific.d4lquestionnaire.sections.anamnesis;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.converter.generic.QuestionnaireResponseItemToEntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.ChronischeLungenkrankheitEvaluation;
import org.hl7.fhir.r4.model.BackboneElement;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public class ChronischeLungenkrankheitEvaluationConverter extends QuestionnaireResponseItemToEntryEntityConverter<ChronischeLungenkrankheitEvaluation> {

    @Override
    protected ChronischeLungenkrankheitEvaluation convertInternal(QuestionnaireResponse.QuestionnaireResponseItemComponent questionnaireResponseItemComponent) {
        ChronischeLungenkrankheitEvaluation chronischeLungenkrankheitEvaluation = new ChronischeLungenkrankheitEvaluation();
        chronischeLungenkrankheitEvaluation.setNameDesProblemsDerDiagnoseValue("Chronische Lungenkrankheit");
        try {
            VorhandenerDefiningCodeConverter.setVorhandenerDefiningCode(getQuestionValueCodeToString(questionnaireResponseItemComponent), chronischeLungenkrankheitEvaluation);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException noSuchMethodException) {
            noSuchMethodException.printStackTrace();
        }
        return chronischeLungenkrankheitEvaluation;
    }
}
