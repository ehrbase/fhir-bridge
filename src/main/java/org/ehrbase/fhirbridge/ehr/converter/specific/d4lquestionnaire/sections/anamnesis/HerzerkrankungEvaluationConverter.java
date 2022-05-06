package org.ehrbase.fhirbridge.ehr.converter.specific.d4lquestionnaire.sections.anamnesis;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.converter.generic.QuestionnaireResponseItemToEntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.HerzerkrankungEvaluation;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

public class HerzerkrankungEvaluationConverter extends QuestionnaireResponseItemToEntryEntityConverter<HerzerkrankungEvaluation> {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    protected HerzerkrankungEvaluation convertInternal(QuestionnaireResponse.QuestionnaireResponseItemComponent questionnaireResponseItemComponent) {
        HerzerkrankungEvaluation herzerkrankungEvaluation = new HerzerkrankungEvaluation();
        herzerkrankungEvaluation.setLanguage(Language.DE);
        herzerkrankungEvaluation.setSubject(new PartySelf());
        herzerkrankungEvaluation.setNameDesProblemsDerDiagnoseValue("Herzerkrankung");
        try {
            VorhandenerDefiningCodeConverter.setVorhandenerDefiningCode(getQuestionValueCodeToString(questionnaireResponseItemComponent), herzerkrankungEvaluation);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException noSuchMethodException) {
            log.error("Conversion error has occurred", noSuchMethodException);
        }
        return herzerkrankungEvaluation;
    }
}
