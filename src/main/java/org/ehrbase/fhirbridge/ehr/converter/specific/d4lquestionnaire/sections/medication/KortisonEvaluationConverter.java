package org.ehrbase.fhirbridge.ehr.converter.specific.d4lquestionnaire.sections.medication;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.QuestionnaireResponseItemToEntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.KortisionEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.StatusDefiningCode2;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

public class KortisonEvaluationConverter extends QuestionnaireResponseItemToEntryEntityConverter<KortisionEvaluation> {

    @Override
    protected KortisionEvaluation convertInternal(QuestionnaireResponse.QuestionnaireResponseItemComponent questionnaireResponseItemComponent) {
        KortisionEvaluation kortisonEvaluation = new KortisionEvaluation();
        String codeString = getQuestionValueCodeToString(questionnaireResponseItemComponent);
        if (codeString.equals(StatusDefiningCode2.JA.getCode())) {
            kortisonEvaluation.setStatusDefiningCode(StatusDefiningCode2.JA);
        } else if (codeString.equals(StatusDefiningCode2.NEIN.getCode())) {
            kortisonEvaluation.setStatusDefiningCode(StatusDefiningCode2.NEIN);
        } else if (codeString.equals(StatusDefiningCode2.ICH_WEISS_ES_NICHT.getCode())) {
            kortisonEvaluation.setStatusDefiningCode(StatusDefiningCode2.ICH_WEISS_ES_NICHT);
        } else {
            throw new ConversionException("The code:" + codeString + " cannot be mapped, please enter a valid code e.g. ja (LA33-6), nein (LA32-8), ich weiss es nicht (LA12688-0)");
        }
        return kortisonEvaluation;
    }
}
