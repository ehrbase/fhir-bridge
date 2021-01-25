package org.ehrbase.fhirbridge.ehr.converter.d4lquestionnaire;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConversionException;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.d4lquestionnaire.sections.Anamnesis;
import org.ehrbase.fhirbridge.ehr.converter.d4lquestionnaire.sections.GeneralInformation;
import org.ehrbase.fhirbridge.ehr.converter.d4lquestionnaire.sections.Medication;
import org.ehrbase.fhirbridge.ehr.converter.d4lquestionnaire.sections.Symptoms;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.D4LQuestionnaireComposition;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

import java.time.OffsetDateTime;
import java.time.temporal.TemporalAccessor;

public class D4lQuestionnaireCompositionConverter implements CompositionConverter<D4LQuestionnaireComposition, QuestionnaireResponse> {
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
    public QuestionnaireResponse fromComposition(D4LQuestionnaireComposition composition) throws CompositionConversionException {
        //TODO Implement
        return null;
    }

    @Override
    public D4LQuestionnaireComposition toComposition(QuestionnaireResponse questionnaireResponse) throws CompositionConversionException {
        D4LQuestionnaireComposition d4LQuestionnaireComposition = new D4LQuestionnaireComposition();
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

    private void setMandatoryFields(D4LQuestionnaireComposition d4LQuestionnaireComposition) {
        //Mandatory Stuff
        d4LQuestionnaireComposition.setLanguage(Language.DE);
        d4LQuestionnaireComposition.setLocation("test");
        d4LQuestionnaireComposition.setSettingDefiningCode(Setting.SECONDARY_MEDICAL_CARE);
        d4LQuestionnaireComposition.setTerritory(Territory.DE);
        d4LQuestionnaireComposition.setCategoryDefiningCode(Category.EVENT);
        d4LQuestionnaireComposition.setComposer(new PartySelf());
    }

    private D4LQuestionnaireComposition populateD4lQuestionnaireComposition(D4LQuestionnaireComposition d4LQuestionnaireComposition) {
        setMandatoryFields(d4LQuestionnaireComposition);
        d4LQuestionnaireComposition.setProblemDiagnose(symptoms.getProblemDiagnose());
        generalInformation.setGeneralInformation(d4LQuestionnaireComposition);
        anamnesis.setVorUndGrunderkrankungen(d4LQuestionnaireComposition);
        medication.setMedikamenteImpfungen(d4LQuestionnaireComposition);
        return d4LQuestionnaireComposition;
    }


}
