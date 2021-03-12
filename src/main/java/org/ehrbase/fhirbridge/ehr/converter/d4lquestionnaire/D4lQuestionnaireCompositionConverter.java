package org.ehrbase.fhirbridge.ehr.converter.d4lquestionnaire;

import org.ehrbase.fhirbridge.ehr.converter.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.d4lquestionnaire.sections.Anamnesis;
import org.ehrbase.fhirbridge.ehr.converter.d4lquestionnaire.sections.GeneralInformation;
import org.ehrbase.fhirbridge.ehr.converter.d4lquestionnaire.sections.Medication;
import org.ehrbase.fhirbridge.ehr.converter.d4lquestionnaire.sections.Symptoms;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.D4LQuestionnaireComposition;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.springframework.lang.NonNull;

import java.time.OffsetDateTime;
import java.time.temporal.TemporalAccessor;

public class D4lQuestionnaireCompositionConverter extends CompositionConverter<QuestionnaireResponse, D4LQuestionnaireComposition> {
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
    public D4LQuestionnaireComposition convertInternal(@NonNull QuestionnaireResponse resource) {
        D4LQuestionnaireComposition d4LQuestionnaireComposition = new D4LQuestionnaireComposition();
        initialiseSections(resource);
        mapSections(resource);
        OffsetDateTime offsetDateTime = OffsetDateTime.from(resource.getAuthoredElement().getValueAsCalendar().toZonedDateTime());
        d4LQuestionnaireComposition.setStartTimeValue(offsetDateTime);
        return populateD4lQuestionnaireComposition(d4LQuestionnaireComposition);
    }

    private void initialiseSections(QuestionnaireResponse questionnaireResponse) {
        TemporalAccessor authored = OffsetDateTime.from(questionnaireResponse.getAuthoredElement().getValueAsCalendar().toZonedDateTime());
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
                    throw new ConversionException("LinkId " + item.getLinkId() + " undefined");
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
