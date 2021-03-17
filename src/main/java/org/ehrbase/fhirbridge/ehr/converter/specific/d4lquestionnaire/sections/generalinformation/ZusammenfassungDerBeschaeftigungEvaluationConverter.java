package org.ehrbase.fhirbridge.ehr.converter.specific.d4lquestionnaire.sections.generalinformation;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.fhirbridge.ehr.converter.generic.QuestionnaireResponseItemToEntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.BerufsbereichDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.BeschaeftigungCluster;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.ZusammenfassungDerBeschaeftigungEvaluation;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

public class ZusammenfassungDerBeschaeftigungEvaluationConverter extends QuestionnaireResponseItemToEntryEntityConverter<ZusammenfassungDerBeschaeftigungEvaluation> {

    @Override
    protected ZusammenfassungDerBeschaeftigungEvaluation convertInternal(QuestionnaireResponse.QuestionnaireResponseItemComponent questionnaireResponseItemComponent) {
        ZusammenfassungDerBeschaeftigungEvaluation zusammenfassungDerBeschaftigungEvaluation = new ZusammenfassungDerBeschaeftigungEvaluation();
        BeschaeftigungCluster beschaftigungCluster = new BeschaeftigungCluster();
        switch (getQuestionValueCodeToString(questionnaireResponseItemComponent)) {
            case "community":
                beschaftigungCluster.setBerufsbereichDefiningCode(BerufsbereichDefiningCode.GEMEINSCHAFTSEINRICHTUNG_SCHULE_KITA_UNIVERSITAET_HEIM_ETC);
                break;
            case "medical":
                beschaftigungCluster.setBerufsbereichDefiningCode(BerufsbereichDefiningCode.MEDIZINISCHEN_BEREICH_PFLEGE_ARZTPRAXIS_ODER_KRANKENHAUS);
                break;
            case "LA46-8":
                beschaftigungCluster.setBerufsbereichDefiningCode(BerufsbereichDefiningCode.SONSTIGES);
                break;
            default:
                throw new UnprocessableEntityException();
        }
        zusammenfassungDerBeschaftigungEvaluation.setBeschaeftigung(beschaftigungCluster);
        return zusammenfassungDerBeschaftigungEvaluation;
    }

}
