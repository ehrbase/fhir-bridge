package org.ehrbase.fhirbridge.ehr.converter.d4lquestionnaire.sections;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.generic.PartySelf;
import com.nedap.archie.rm.support.identification.TerminologyId;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
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
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.SchwangerschaftsstatusBeliebigesEreignisChoice;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.SchwangerschaftsstatusBeliebigesEreignisPointEvent;
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

    public GeneralInformation(TemporalAccessor authored) {
        super(authored);
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
        switch (question.getLinkId()) {
            case P0:
                mapAge(getQuestionValueCodeToString(question));
                break;
            case P1:
                mapAgeOver60(getQuestionLoincYesNoToBoolean(question));
                break;
            case P2:
                mapWohnsituationEvaluation(getQuestionValueCodeToString(question));
                break;
            case P3:
                mapPrivateCaregiver(getQuestionLoincYesNoToBoolean(question));
                break;
            case P4:
                mapOccupation(getQuestionValueCodeToString(question));
                break;
            case P5:
                mapSmoker(getQuestionLoincYesNoToBoolean(question));
                break;
            case P6:
                mapPregnant(getQuestionValueCodeToString(question));
                break;
            default:
                throw new UnprocessableEntityException("LinkId " + question.getLinkId() + " undefined");

        }
    }

    private AlterObservation getAlterObservation(){
        if(alterObservationQuestion.isEmpty()){
            AlterObservation alterObservation = new AlterObservation();
            alterObservation.setLanguage(Language.DE);
            alterObservation.setSubject(new PartySelf());
            alterObservation.setTimeValue(super.authored);
            alterObservation.setOriginValue(super.authored);
            return alterObservation;
        }else{
            return alterObservationQuestion.get();
        }
    }

    private void mapAgeOver60(Boolean over60) {
        AlterObservation alterObservation = getAlterObservation();
        if (over60) {
            alterObservation.setAelterOderGleich65JahreAltDefiningCode(AelterOderGleich65JahreAltDefiningCode.JA);
        } else {
            alterObservation.setAelterOderGleich65JahreAltDefiningCode(AelterOderGleich65JahreAltDefiningCode.NEIN);
        }
    }

    protected void mapAge(String age) {
        AlterObservation alterObservation = getAlterObservation();
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
        alterObservationQuestion = Optional.of(alterObservation);
    }

    protected void mapWohnsituationEvaluation(String housingSituation) {
        WohnsituationEvaluation wohnsituationEvaluation = new WohnsituationEvaluation();
        if (housingSituation.equals(WohnsituationDefiningCode.WOHNT_MIT_ANDEREN_ZUSAMMEN.getCode())) {
            wohnsituationEvaluation.setWohnsituationDefiningCode(WohnsituationDefiningCode.WOHNT_MIT_ANDEREN_ZUSAMMEN);
        } else if (housingSituation.equals(WohnsituationDefiningCode.ALLEIN_WOHNEND.getCode())) {
            wohnsituationEvaluation.setWohnsituationDefiningCode(WohnsituationDefiningCode.ALLEIN_WOHNEND);
        } else if (!housingSituation.equals("")) {
            throw new UnprocessableEntityException("The code for Wohnungsituation:" + housingSituation + " cannot be mapped, please enter a valid code e.g. Wohnt mit anderen zusammen (LOINC: LA9996-5)");
        }
        wohnsituationEvaluation.setLanguage(Language.DE);
        wohnsituationEvaluation.setSubject(new PartySelf());

        wohnsituationEvaluationQuestion = Optional.of(wohnsituationEvaluation);
    }

    protected void mapPrivateCaregiver(Boolean isPrivateCaregiver) {
        PflegetaetigkeitEvaluation pflegetatigkeitEvaluation = getPflegetatigkeitEvaluation();
        pflegetatigkeitEvaluation.setPrivatValue(isPrivateCaregiver);
        pflegetatigkeitEvaluationQuestion = Optional.of(pflegetatigkeitEvaluation);
    }


    protected void mapOccupation(String occupationClass) {
        ZusammenfassungDerBeschaeftigungEvaluation zusammenfassungDerBeschaftigungEvaluation = new ZusammenfassungDerBeschaeftigungEvaluation();
        zusammenfassungDerBeschaftigungEvaluation.setLanguage(Language.DE);
        zusammenfassungDerBeschaftigungEvaluation.setSubject(new PartySelf());
        BeschaeftigungCluster beschaftigungCluster = new BeschaeftigungCluster();
        switch (occupationClass) {
            case "community":
                beschaftigungCluster.setBerufsbereichDefiningCode(BerufsbereichDefiningCode.GEMEINSCHAFTSEINRICHTUNG_SCHULE_KITA_UNIVERSITAET_HEIM_ETC);
                break;
            case "medical":
                beschaftigungCluster.setBerufsbereichDefiningCode(BerufsbereichDefiningCode.MEDIZINISCHEN_BEREICH_PFLEGE_ARZTPRAXIS_ODER_KRANKENHAUS);
                break;
            case "LA46-8":
                beschaftigungCluster.setBerufsbereichDefiningCode(BerufsbereichDefiningCode.SONSTIGES);
                break;
            default:
                throw new UnprocessableEntityException();
        }

        zusammenfassungDerBeschaftigungEvaluation.setBeschaeftigung(beschaftigungCluster);
        zusammenfassungDerBeschaftigungEvaluationQuestion = Optional.of(zusammenfassungDerBeschaftigungEvaluation);

    }

    private PflegetaetigkeitEvaluation getPflegetatigkeitEvaluation() {
        return pflegetatigkeitEvaluationQuestion.orElseGet(this::createPflegetatigkeitEvaluation);
    }

    protected PflegetaetigkeitEvaluation createPflegetatigkeitEvaluation() {
        PflegetaetigkeitEvaluation pflegetatigkeitEvaluation = new PflegetaetigkeitEvaluation();
        pflegetatigkeitEvaluation.setLanguage(Language.DE);
        pflegetatigkeitEvaluation.setSubject(new PartySelf());

        pflegetatigkeitEvaluation.setAnzahlDerGepflegtenPersonenValue("Mindestens eine Person");
        pflegetatigkeitEvaluation.setFrequenzDerPflegeValue("Mindestens einmal die Woche");

        PflegetaetigkeitGrundFuerDieTaetigkeitElement pflegetatigkeitGrundFurDieTatigkeitElement = new PflegetaetigkeitGrundFuerDieTaetigkeitElement();
        pflegetatigkeitGrundFurDieTatigkeitElement.setValue("alterbedingten Beschwerden, chronischen Erkrankungen oder Gebrechlichkeit");

        pflegetatigkeitEvaluation.setGrundFuerDieTaetigkeit(List.of(pflegetatigkeitGrundFurDieTatigkeitElement));
        return pflegetatigkeitEvaluation;
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


    protected void mapSmoker(Boolean isSmoker) {
        ZusammenfassungRauchverhaltenEvaluation zusammenfassungRauchverhaltenEvaluation = new ZusammenfassungRauchverhaltenEvaluation();
        zusammenfassungRauchverhaltenEvaluation.setLanguage(Language.DE);
        zusammenfassungRauchverhaltenEvaluation.setSubject(new PartySelf());
        if (isSmoker) {
            zusammenfassungRauchverhaltenEvaluation.setRaucherDefiningCode(RaucherDefiningCode.JA);
        } else {
            zusammenfassungRauchverhaltenEvaluation.setRaucherDefiningCode(RaucherDefiningCode.NEIN);
        }
        zusammenfassungRauchverhaltenEvaluationQuestion = Optional.of(zusammenfassungRauchverhaltenEvaluation);
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

    protected void mapPregnant(String pregnantLoincCode) {
        SchwangerschaftsstatusObservation stillzeitEvaluation = new SchwangerschaftsstatusObservation();
        stillzeitEvaluation.setStatusDefiningCode(pregnantLoincToStatusCode(pregnantLoincCode));
        stillzeitEvaluation.setLanguage(Language.DE);
        stillzeitEvaluation.setSubject(new PartySelf());
        stillzeitEvaluation.setOriginValue(this.authored);
        stillzeitEvaluation.setTimeValue(this.authored);
        schwangerschaftsstatusObservationQuestion = Optional.of(stillzeitEvaluation);
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
