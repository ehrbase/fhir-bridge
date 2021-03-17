package org.ehrbase.fhirbridge.ehr.converter.specific.d4lquestionnaire;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.QuestionnaireResponseToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.d4lquestionnaire.sections.anamnesis.Anamnesis;
import org.ehrbase.fhirbridge.ehr.converter.specific.d4lquestionnaire.sections.generalinformation.GeneralInformation;
import org.ehrbase.fhirbridge.ehr.converter.specific.d4lquestionnaire.sections.medication.Medication;
import org.ehrbase.fhirbridge.ehr.converter.specific.d4lquestionnaire.sections.symptoms.Symptoms;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.D4LQuestionnaireComposition;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.springframework.lang.NonNull;

public class D4lQuestionnaireCompositionConverter extends QuestionnaireResponseToCompositionConverter<D4LQuestionnaireComposition> {
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
        initialiseSections(d4LQuestionnaireComposition);
        mapSections(resource);
        return populateD4lQuestionnaireComposition(d4LQuestionnaireComposition);
    }

    private void initialiseSections(D4LQuestionnaireComposition d4LQuestionnaireComposition) {
        this.generalInformation = new GeneralInformation(d4LQuestionnaireComposition.getLanguage());
        this.symptoms = new Symptoms(d4LQuestionnaireComposition.getLanguage());
        this.anamnesis = new Anamnesis(d4LQuestionnaireComposition.getLanguage());
        this.medication = new Medication(d4LQuestionnaireComposition.getLanguage());
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
        anamnesis.setVorUndGrunderkrankungen(d4LQuestionnaireComposition, d4LQuestionnaireComposition.getLanguage());
        medication.setMedikamenteImpfungen(d4LQuestionnaireComposition);
        return d4LQuestionnaireComposition;
    }
}
