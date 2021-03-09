package org.ehrbase.fhirbridge.ehr.converter.d4lquestionnaire;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.fhirbridge.ehr.converter.AbstractCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.d4lquestionnaire.sections.Anamnesis;
import org.ehrbase.fhirbridge.ehr.converter.d4lquestionnaire.sections.GeneralInformation;
import org.ehrbase.fhirbridge.ehr.converter.d4lquestionnaire.sections.Medication;
import org.ehrbase.fhirbridge.ehr.converter.d4lquestionnaire.sections.Symptoms;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.D4LQuestionnaireComposition;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.springframework.lang.NonNull;

import java.time.OffsetDateTime;
import java.time.temporal.TemporalAccessor;

public class D4lQuestionnaireCompositionConverter extends AbstractCompositionConverter<QuestionnaireResponse, D4LQuestionnaireComposition> {
    private static final String P = "P";
    private static final String C = "C";
    private static final String S = "S";
    private static final String D = "D";
    private static final String M = "M";

    GeneralInformation generalInformation;
    Symptoms symptoms;
    Anamnesis anamnesis;
    Medication medication;


    @Override
    public D4LQuestionnaireComposition convert(@NonNull QuestionnaireResponse questionnaireResponse) {
        D4LQuestionnaireComposition d4LQuestionnaireComposition = new D4LQuestionnaireComposition();
        mapCommonAttributes(questionnaireResponse, d4LQuestionnaireComposition);
        initialiseSections(questionnaireResponse);
        mapSections(questionnaireResponse);
        OffsetDateTime offsetDateTime = OffsetDateTime.from(questionnaireResponse.getAuthoredElement().getValueAsCalendar().toZonedDateTime());
        d4LQuestionnaireComposition.setStartTimeValue(offsetDateTime);
        return populateD4lQuestionnaireComposition(d4LQuestionnaireComposition);
    }

    private void initialiseSections(QuestionnaireResponse questionnaireResponse) {
        OffsetDateTime offsetDateTime = OffsetDateTime.from(questionnaireResponse.getAuthoredElement().getValueAsCalendar().toZonedDateTime());
        TemporalAccessor authored = offsetDateTime;
        this.generalInformation = new GeneralInformation(authored);
        this.symptoms = new Symptoms(authored);
        this.anamnesis = new Anamnesis(authored);
        this.medication = new Medication(authored);
    }

    private void mapSections(QuestionnaireResponse questionnaireResponse) {
        for (QuestionnaireResponse.QuestionnaireResponseItemComponent item : questionnaireResponse.getItem()) {
            switch (item.getLinkId()) {
                case P:
                    generalInformation.map(item.getItem());
                    break;
                case C:
                    generalInformation.mapContactWithInfectedQuestion(item.getItem());
                    break;
                case S:
                    symptoms.map(item.getItem());
                    break;
                case D:
                    anamnesis.map(item.getItem());
                    break;
                case M:
                    medication.map(item.getItem());
                    break;
                default:
                    throw new UnprocessableEntityException("LinkId " + item.getLinkId() + " undefined");
            }
        }
    }

    private D4LQuestionnaireComposition populateD4lQuestionnaireComposition(D4LQuestionnaireComposition d4LQuestionnaireComposition) {
        d4LQuestionnaireComposition.setProblemDiagnose(symptoms.getProblemDiagnose());
        generalInformation.setGeneralInformation(d4LQuestionnaireComposition);
        anamnesis.setVorUndGrunderkrankungen(d4LQuestionnaireComposition);
        medication.setMedikamenteImpfungen(d4LQuestionnaireComposition);
        return d4LQuestionnaireComposition;
    }
}
