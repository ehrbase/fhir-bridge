package org.ehrbase.fhirbridge.ehr.converter.specific.d4lquestionnaire.sections.generalinformation;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.QuestionnaireResponseItemToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.SchwangerschaftsstatusObservation;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.StatusDefiningCode;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

public class SchwangerschaftsstatusObservationConverter extends QuestionnaireResponseItemToObservationConverter<SchwangerschaftsstatusObservation> {

    @Override
    protected SchwangerschaftsstatusObservation convertInternal(QuestionnaireResponse.QuestionnaireResponseItemComponent questionnaireResponseItemComponent) {
        SchwangerschaftsstatusObservation stillzeitEvaluation = new SchwangerschaftsstatusObservation();
        stillzeitEvaluation.setStatusDefiningCode(pregnantLoincToStatusCode(getQuestionValueCodeToString(questionnaireResponseItemComponent)));
        return stillzeitEvaluation;
    }

    private StatusDefiningCode pregnantLoincToStatusCode(String pregnantCode) {
        switch (pregnantCode) {
            case "LA15173-0":
                return StatusDefiningCode.SCHWANGER;
            case "LA26683-5":
                return StatusDefiningCode.NICHT_SCHWANGER;
            case "LA4489-6":
                return StatusDefiningCode.UNBEKANNT;
            default:
                throw new ConversionException("The code for Pregnancy:" + pregnantCode + " cannot be mapped, please enter a valid code e.g. pregnant (LA15173-0), not pregnant (LA26683-5) or unknown(LA4489-6) )");
        }
    }

}
