package org.ehrbase.fhirbridge.ehr.converter.specific.d4lquestionnaire.sections.generalinformation;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.converter.specific.d4lquestionnaire.sections.QuestionnaireSection;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.D4LQuestionnaireComposition;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.AlterObservation;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.AusschlussPflegetaetigkeitEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.BerufsbereichDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.KontaktAction;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.PflegetaetigkeitEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.SchwangerschaftsstatusObservation;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.WohnsituationEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.ZusammenfassungDerBeschaeftigungEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.ZusammenfassungRauchverhaltenEvaluation;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

import java.time.temporal.TemporalAccessor;
import java.util.List;
import java.util.Optional;

public class GeneralInformation extends QuestionnaireSection {
    private static final String P0 = "P0";
    private static final String P1 = "P1";
    private static final String P2 = "P2";
    private static final String P3 = "P3";
    private static final String P4 = "P4";
    private static final String P5 = "P5";
    private static final String P6 = "P6";
    private static final String C0 = "C0";
    private static final String CZ = "CZ";


    private Optional<AlterObservation> alterObservationQuestion = Optional.empty();
    private Optional<WohnsituationEvaluation> wohnsituationEvaluationQuestion = Optional.empty();
    private Optional<PflegetaetigkeitEvaluation> pflegetatigkeitEvaluationQuestion = Optional.empty();
    private Optional<ZusammenfassungDerBeschaeftigungEvaluation> zusammenfassungDerBeschaftigungEvaluationQuestion = Optional.empty();
    private Optional<AusschlussPflegetaetigkeitEvaluation> ausschlussPflegetatigkeit = Optional.empty();
    private Optional<ZusammenfassungRauchverhaltenEvaluation> zusammenfassungRauchverhaltenEvaluationQuestion = Optional.empty();
    private Optional<SchwangerschaftsstatusObservation> schwangerschaftsstatusObservationQuestion = Optional.empty();
    private Optional<KontaktAction> contactWithInfectedQuestion = Optional.empty();

    public GeneralInformation(Language language, TemporalAccessor startTimeValue) {
        super(language, startTimeValue);
    }


    @Override
    public void map(List<QuestionnaireResponse.QuestionnaireResponseItemComponent> item) {
        AlterObservationConverter alterObservationConverter = new AlterObservationConverter();
        for (QuestionnaireResponse.QuestionnaireResponseItemComponent question : item) {
            if (getValueCode(question).isPresent() || getValueAsDate(question).isPresent()) {
                mapGeneralInformationQuestions(question, alterObservationConverter);
            }

        }
    }

    private void mapGeneralInformationQuestions(QuestionnaireResponse.QuestionnaireResponseItemComponent question, AlterObservationConverter alterObservationConverter) {
        switch (question.getLinkId()) {
            case P0:
            case P1:
                alterObservationQuestion = Optional.of(alterObservationConverter.convert(question, language, authored));
                break;
            case P2:
                wohnsituationEvaluationQuestion = Optional.of(new WohnungsEvaluationConverter().convert(question, language, authored));
                break;
            case P3:
                pflegetatigkeitEvaluationQuestion = Optional.of(new PflegetaetigkeitEvaluationConverter().convert(question, language, authored));
                break;
            case P4:
                zusammenfassungDerBeschaftigungEvaluationQuestion = Optional.of(new ZusammenfassungDerBeschaeftigungEvaluationConverter().convert(question, language, authored));
                break;
            case P5:
                zusammenfassungRauchverhaltenEvaluationQuestion = Optional.of(new ZusammenfassungRauchverhaltenEvaluationConverter().convert(question, language, authored));
                break;
            case P6:
                schwangerschaftsstatusObservationQuestion = Optional.of(new SchwangerschaftsstatusObservationConverter().convert(question, language, authored));
                break;
            default:
                throw new ConversionException("LinkId " + question.getLinkId() + " undefined");

        }
    }


    private void checkPflegetatigkeitAusschluss() {
        if (pflegetatigkeitEvaluationQuestion.isPresent()) {
            if (!pflegetatigkeitEvaluationQuestion.get().isPrivatValue() && !isProfessionalCareGiver()) {
                pflegetatigkeitEvaluationQuestion = Optional.empty();
                setAusschlussPflegetatigkeitEvaluation();
            }
        }
    }

    private boolean isProfessionalCareGiver() {
        return zusammenfassungDerBeschaftigungEvaluationQuestion.get().getBeschaeftigung().getBerufsbereichDefiningCode().equals(BerufsbereichDefiningCode.MEDIZINISCHEN_BEREICH_PFLEGE_ARZTPRAXIS_ODER_KRANKENHAUS);
    }

    private void setAusschlussPflegetatigkeitEvaluation() {
        AusschlussPflegetaetigkeitEvaluation ausschlussPflegetatigkeitEvaluation = new AusschlussPflegetaetigkeitEvaluation();
        ausschlussPflegetatigkeitEvaluation.setLanguage(Language.DE);
        ausschlussPflegetatigkeitEvaluation.setSubject(new PartySelf());
        ausschlussPflegetatigkeitEvaluation.setAussageUeberDenAusschlussValue("Pflegetätigkeit");
        ausschlussPflegetatigkeit = Optional.of(ausschlussPflegetatigkeitEvaluation);
    }

    public void mapContactWithInfectedQuestion(List<QuestionnaireResponse.QuestionnaireResponseItemComponent> item) {
        KontaktActionConverter kontaktActionConverter = new KontaktActionConverter();
        for (QuestionnaireResponse.QuestionnaireResponseItemComponent question : item) {
            contactWithInfectedQuestion = Optional.of(kontaktActionConverter.convert(question, language, authored));
        }
    }

    public void setGeneralInformation(D4LQuestionnaireComposition d4LQuestionnaireComposition) {
        alterObservationQuestion.ifPresent(d4LQuestionnaireComposition::setAlter);
        wohnsituationEvaluationQuestion.ifPresent(d4LQuestionnaireComposition::setWohnsituation);
        checkPflegetatigkeitAusschluss();
        pflegetatigkeitEvaluationQuestion.ifPresent(d4LQuestionnaireComposition::setPflegetaetigkeit);
        ausschlussPflegetatigkeit.ifPresent(d4LQuestionnaireComposition::setAusschlussPflegetaetigkeit);
        zusammenfassungDerBeschaftigungEvaluationQuestion.ifPresent(d4LQuestionnaireComposition::setZusammenfassungDerBeschaeftigung);
        zusammenfassungRauchverhaltenEvaluationQuestion.ifPresent(d4LQuestionnaireComposition::setZusammenfassungRauchverhalten);
        schwangerschaftsstatusObservationQuestion.ifPresent(d4LQuestionnaireComposition::setSchwangerschaftsstatus);
        contactWithInfectedQuestion.ifPresent(d4LQuestionnaireComposition::setKontakt);
    }

}
