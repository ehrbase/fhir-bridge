package org.ehrbase.fhirbridge.ehr.converter.specific.d4lquestionnaire.sections.generalinformation;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.converter.generic.QuestionnaireResponseItemToEntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.AdipositasEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.WohnsituationDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.WohnsituationEvaluation;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

public class WohnungsEvaluationConverter extends QuestionnaireResponseItemToEntryEntityConverter<WohnsituationEvaluation> {

    @Override
    protected WohnsituationEvaluation convertInternal(QuestionnaireResponse.QuestionnaireResponseItemComponent questionnaireResponseItemComponent) {
        String housingSituation = getQuestionValueCodeToString(questionnaireResponseItemComponent);
        WohnsituationEvaluation wohnsituationEvaluation = new WohnsituationEvaluation();
        if (housingSituation.equals(WohnsituationDefiningCode.WOHNT_MIT_ANDEREN_ZUSAMMEN.getCode())) {
            wohnsituationEvaluation.setWohnsituationDefiningCode(WohnsituationDefiningCode.WOHNT_MIT_ANDEREN_ZUSAMMEN);
        } else if (housingSituation.equals(WohnsituationDefiningCode.ALLEIN_WOHNEND.getCode())) {
            wohnsituationEvaluation.setWohnsituationDefiningCode(WohnsituationDefiningCode.ALLEIN_WOHNEND);
        } else if (!housingSituation.equals("")) {
            throw new ConversionException("The code for Wohnungsituation:" + housingSituation + " cannot be mapped, please enter a valid code e.g. Wohnt mit anderen zusammen (LOINC: LA9996-5)");
        }
        wohnsituationEvaluation.setLanguage(Language.DE);
        wohnsituationEvaluation.setSubject(new PartySelf());

        return wohnsituationEvaluation;
    }
}
