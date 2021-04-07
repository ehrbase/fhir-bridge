package org.ehrbase.fhirbridge.ehr.converter.specific.d4lquestionnaire.sections.generalinformation;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.support.identification.TerminologyId;
import org.ehrbase.fhirbridge.ehr.converter.generic.QuestionnaireResponseItemToActionConverter;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.AelterOderGleich65JahreAltDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.KontaktAction;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;

public class KontaktActionConverter extends QuestionnaireResponseItemToActionConverter<KontaktAction> {
    KontaktAction kontaktAction = new KontaktAction();

    private static final String C0 = "C0";
    private static final String CZ = "CZ";

    @Override
    protected KontaktAction convertInternal(QuestionnaireResponse.QuestionnaireResponseItemComponent questionnaireResponseItemComponent) {
        if(questionnaireResponseItemComponent.getLinkId().equals(C0)){
            mapContactWithInfected(getQuestionLoincYesNoToBoolean(questionnaireResponseItemComponent));
        }else if(questionnaireResponseItemComponent.getLinkId().equals(CZ)){
            mapDateOfContactInfected(getValueAsDate(questionnaireResponseItemComponent).get());
        } else {
            throw new UnprocessableEntityException("LinkId " + questionnaireResponseItemComponent.getLinkId() + " undefined");
        }
        return kontaktAction;
    }

    private void mapDateOfContactInfected(TemporalAccessor date) {
        LocalDateTime localDate = LocalDate.parse(date.toString()).atTime(1, 0);
        kontaktAction.setBeginnValue(localDate);
        kontaktAction.setEndeValue(localDate);
    }

    private void mapContactWithInfected(Boolean hadContact) {
        DvCodedText dvCodedText = new DvCodedText("Done", new CodePhrase(new TerminologyId("local"), "at0016"));
        kontaktAction.setCurrentState(dvCodedText);
        if (hadContact) {
            kontaktAction.setKontaktZuEinemBestaetigtenFallDefiningCode(AelterOderGleich65JahreAltDefiningCode.JA);
        } else {
            kontaktAction.setKontaktZuEinemBestaetigtenFallDefiningCode(AelterOderGleich65JahreAltDefiningCode.NEIN);
        }
    }
}
