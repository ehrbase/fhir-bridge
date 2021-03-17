package org.ehrbase.fhirbridge.ehr.converter.specific.d4lquestionnaire.sections.generalinformation;

import org.ehrbase.fhirbridge.ehr.converter.generic.QuestionnaireResponseItemToEntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.SchwangerschaftsstatusObservation;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

public class SchwangerschaftsstatusObservationConverter extends QuestionnaireResponseItemToEntryEntityConverter<SchwangerschaftsstatusObservation> {

    @Override
    protected SchwangerschaftsstatusObservation convertInternal(QuestionnaireResponse.QuestionnaireResponseItemComponent questionnaireResponseItemComponent) {
        return null;
    }
}
