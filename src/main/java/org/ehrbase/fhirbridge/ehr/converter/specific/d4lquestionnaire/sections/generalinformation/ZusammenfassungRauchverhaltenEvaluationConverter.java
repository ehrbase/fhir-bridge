package org.ehrbase.fhirbridge.ehr.converter.specific.d4lquestionnaire.sections.generalinformation;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.converter.generic.QuestionnaireResponseItemToEntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.RaucherDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.ZusammenfassungRauchverhaltenEvaluation;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

public class ZusammenfassungRauchverhaltenEvaluationConverter extends QuestionnaireResponseItemToEntryEntityConverter<ZusammenfassungRauchverhaltenEvaluation> {
    @Override
    protected ZusammenfassungRauchverhaltenEvaluation convertInternal(QuestionnaireResponse.QuestionnaireResponseItemComponent questionnaireResponseItemComponent) {
        ZusammenfassungRauchverhaltenEvaluation zusammenfassungRauchverhaltenEvaluation = new ZusammenfassungRauchverhaltenEvaluation();
        zusammenfassungRauchverhaltenEvaluation.setLanguage(Language.DE);
        zusammenfassungRauchverhaltenEvaluation.setSubject(new PartySelf());
        if (getQuestionLoincYesNoToBoolean(questionnaireResponseItemComponent)) {
            zusammenfassungRauchverhaltenEvaluation.setRaucherDefiningCode(RaucherDefiningCode.JA);
        } else {
            zusammenfassungRauchverhaltenEvaluation.setRaucherDefiningCode(RaucherDefiningCode.NEIN);
        }
        return zusammenfassungRauchverhaltenEvaluation;
    }
}
