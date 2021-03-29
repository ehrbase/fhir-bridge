package org.ehrbase.fhirbridge.ehr.converter.specific.d4lquestionnaire.sections.anamnesis;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.converter.generic.QuestionnaireResponseItemToEntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.HerzerkrankungEvaluation;
import org.hl7.fhir.r4.model.BackboneElement;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public class HerzerkrankungEvaluationConverter extends QuestionnaireResponseItemToEntryEntityConverter<HerzerkrankungEvaluation> {

    @Override
    protected HerzerkrankungEvaluation convertInternal(QuestionnaireResponse.QuestionnaireResponseItemComponent questionnaireResponseItemComponent) {
        HerzerkrankungEvaluation herzerkrankungEvaluation = new HerzerkrankungEvaluation();
        herzerkrankungEvaluation.setLanguage(Language.DE);
        herzerkrankungEvaluation.setSubject(new PartySelf());
        herzerkrankungEvaluation.setNameDesProblemsDerDiagnoseValue("Herzerkrankung");
        try {
            VorhandenerDefiningCodeConverter.setVorhandenerDefiningCode(getQuestionValueCodeToString(questionnaireResponseItemComponent), herzerkrankungEvaluation);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException noSuchMethodException) {
            noSuchMethodException.printStackTrace();
        }
        return herzerkrankungEvaluation;
    }
}
