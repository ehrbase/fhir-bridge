package org.ehrbase.fhirbridge.ehr.converter.specific.d4lquestionnaire.sections.generalinformation;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.fhirbridge.ehr.converter.generic.QuestionnaireResponseItemToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.AelterOderGleich65JahreAltDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.AlterObservation;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.AltersklasseDefiningCode;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

public class AlterObservationConverter extends QuestionnaireResponseItemToObservationConverter<AlterObservation> {
    AlterObservation alterObservation = new AlterObservation();

    @Override
    protected AlterObservation convertInternal(QuestionnaireResponse.QuestionnaireResponseItemComponent questionnaireResponseItemComponent) {
        convertAge(questionnaireResponseItemComponent);
        convertAgeOver60(getQuestionLoincYesNoToBoolean(questionnaireResponseItemComponent));
        return alterObservation;
    }

    private void convertAgeOver60(Boolean over60) {
        if (over60) {
            alterObservation.setAelterOderGleich65JahreAltDefiningCode(AelterOderGleich65JahreAltDefiningCode.JA);
        } else {
            alterObservation.setAelterOderGleich65JahreAltDefiningCode(AelterOderGleich65JahreAltDefiningCode.NEIN);
        }
    }

    private void convertAge(QuestionnaireResponse.QuestionnaireResponseItemComponent questionnaireResponseItemComponent) {
        String age = getQuestionValueCodeToString(questionnaireResponseItemComponent);
        if (age.equals(AltersklasseDefiningCode.JUENGER_ALS40.getCode())) {
            alterObservation.setAltersklasseDefiningCode(AltersklasseDefiningCode.JUENGER_ALS40);
        } else if (age.equals(AltersklasseDefiningCode.N6170.getCode())) {
            alterObservation.setAltersklasseDefiningCode(AltersklasseDefiningCode.N6170);
        } else if (age.equals(AltersklasseDefiningCode.UEBER80.getCode())) {
            alterObservation.setAltersklasseDefiningCode(AltersklasseDefiningCode.UEBER80);
        } else if (age.equals(AltersklasseDefiningCode.N7180.getCode())) {
            alterObservation.setAltersklasseDefiningCode(AltersklasseDefiningCode.N7180);
        } else if (age.equals(AltersklasseDefiningCode.N4050.getCode())) {
            alterObservation.setAltersklasseDefiningCode(AltersklasseDefiningCode.N4050);
        } else if (age.equals(AltersklasseDefiningCode.N5160.getCode())) {
            alterObservation.setAltersklasseDefiningCode(AltersklasseDefiningCode.N5160);
        } else if (!age.equals("")) {
            throw new UnprocessableEntityException("The code for age:" + age + " cannot be mapped, plese enter a valid code e.g. 61-70");
        }
    }
}
