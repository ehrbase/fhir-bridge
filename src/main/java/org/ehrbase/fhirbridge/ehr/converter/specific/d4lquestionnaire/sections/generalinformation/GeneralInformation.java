package org.ehrbase.fhirbridge.ehr.converter.specific.d4lquestionnaire.sections.generalinformation;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.generic.PartySelf;
import com.nedap.archie.rm.support.identification.TerminologyId;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.converter.specific.d4lquestionnaire.sections.QuestionnaireSection;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.D4LQuestionnaireComposition;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.AelterOderGleich65JahreAltDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.AlterObservation;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.AltersklasseDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.AusschlussPflegetaetigkeitEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.BerufsbereichDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.BeschaeftigungCluster;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.KontaktAction;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.PflegetaetigkeitEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.PflegetaetigkeitGrundFuerDieTaetigkeitElement;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.RaucherDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.SchwangerschaftsstatusObservation;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.StatusDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.WohnsituationDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.WohnsituationEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.ZusammenfassungDerBeschaeftigungEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.ZusammenfassungRauchverhaltenEvaluation;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    public GeneralInformation(Language language) {
        super(language);
    }


    @Override
    public void map(List<QuestionnaireResponse.QuestionnaireResponseItemComponent> item) {
        for (QuestionnaireResponse.QuestionnaireResponseItemComponent question : item) {
            if (getValueCode(question).isPresent() || getValueAsDate(question).isPresent()) {
                mapGeneralInformationQuestions(question);
            }

        }
    }

    private void mapGeneralInformationQuestions(QuestionnaireResponse.QuestionnaireResponseItemComponent question) {
        AlterObservationConverter alterObservationConverter = new AlterObservationConverter();
        switch (question.getLinkId()) {
            case P0:
            case P1:
                alterObservationQuestion = Optional.of(alterObservationConverter.convert(question, language));
                break;
            case P2:
                wohnsituationEvaluationQuestion = Optional.of(new WohnungsEvaluationConverter().convert(question, language));
                break;
            case P3:
                pflegetatigkeitEvaluationQuestion = Optional.of(new PflegetaetigkeitEvaluationConverter().convert(question, language));
                break;
            case P4:
                zusammenfassungDerBeschaftigungEvaluationQuestion = Optional.of(new ZusammenfassungDerBeschaeftigungEvaluationConverter().convert(question, language));
                break;
            case P5:
                zusammenfassungRauchverhaltenEvaluationQuestion = Optional.of(new ZusammenfassungRauchverhaltenEvaluationConverter().convert(question, language));
                break;
            case P6:
                schwangerschaftsstatusObservationQuestion = Optional.of(new SchwangerschaftsstatusObservationConverter().convert(question, language));
                break;
            default:
                throw new UnprocessableEntityException("LinkId " + question.getLinkId() + " undefined");

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


    private StatusDefiningCode pregnantLoincToStatusCode(String pregnantCode) {
        switch (pregnantCode) {
            case "LA15173-0":
                return StatusDefiningCode.SCHWANGER;
            case "LA26683-5":
                return StatusDefiningCode.NICHT_SCHWANGER;
            case "LA4489-6":
                return StatusDefiningCode.UNBEKANNT;
            default:
                throw new UnprocessableEntityException("The code for Pregnancy:" + pregnantCode + " cannot be mapped, please enter a valid code e.g. pregnant (LA15173-0), not pregnant (LA26683-5) or unknown(LA4489-6) )");
        }
    }


    public void mapContactWithInfectedQuestion(List<QuestionnaireResponse.QuestionnaireResponseItemComponent> item) {
        for (QuestionnaireResponse.QuestionnaireResponseItemComponent question : item) {
            if (question.getLinkId().equals(C0) && getValueCode(question).isPresent()) {
                mapContactWithInfected(getQuestionLoincYesNoToBoolean(question));
            } else if (question.getLinkId().equals(CZ) && getValueAsDate(question).isPresent()) {
                mapDateOfContactInfected(getValueAsDate(question).get());
            } else {
                throw new UnprocessableEntityException("LinkId " + question.getLinkId() + " undefined");
            }
        }
    }

    private void mapDateOfContactInfected(TemporalAccessor date) {
        KontaktAction kontaktAction = getKontaktAction();
        LocalDateTime localDate = LocalDate.parse(date.toString()).atTime(1, 0);
        kontaktAction.setBeginnValue(localDate);
        kontaktAction.setEndeValue(localDate);
        contactWithInfectedQuestion = Optional.of(kontaktAction);
    }

    public void mapContactWithInfected(Boolean hadContact) {
        KontaktAction kontaktAction = getKontaktAction();
        DvCodedText dvCodedText = new DvCodedText("Done", new CodePhrase(new TerminologyId("local"), "at0016"));
        kontaktAction.setCurrentState(dvCodedText);
        if (hadContact) {
            kontaktAction.setKontaktZuEinemBestaetigtenFallDefiningCode(AelterOderGleich65JahreAltDefiningCode.JA);
        } else {
            kontaktAction.setKontaktZuEinemBestaetigtenFallDefiningCode(AelterOderGleich65JahreAltDefiningCode.NEIN);
        }
        contactWithInfectedQuestion = Optional.of(kontaktAction);
    }

    private KontaktAction getKontaktAction() {
        if (contactWithInfectedQuestion.isPresent()) {
            return contactWithInfectedQuestion.get();
        } else {
            KontaktAction kontaktAction = new KontaktAction();
            kontaktAction.setLanguage(Language.DE);
            kontaktAction.setSubject(new PartySelf());
            kontaktAction.setTimeValue(this.authored);
            return kontaktAction;
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
