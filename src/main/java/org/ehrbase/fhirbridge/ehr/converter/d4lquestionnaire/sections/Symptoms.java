package org.ehrbase.fhirbridge.ehr.converter.d4lquestionnaire.sections;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.*;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.SymptomComposition;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Symptoms extends QuestionnaireSection {
    private static final String S0 = "S0";
    private static final String S1 = "S1";
    private static final String S3 = "S3";
    private static final String S4 = "S4";
    private static final String S5 = "S5";
    private static final String S6 = "S6";
    private static final String S7 = "S7";
    private static final String S8 = "S8";
    private static final String S9 = "S9";
    private static final String SA = "SA";
    private static final String SB = "SB";
    private static final String SC = "SC";
    private static final String SZ = "SZ";

    private Optional<FieberInDenLetzten24StundenCluster> fever24hQuestion = Optional.empty();
    private Optional<ProblemDiagnoseEvaluation> problemDiagnoseEvaluationQuestion= Optional.empty();

    public Symptoms(TemporalAccessor authored) {
        super(authored);
    }

    @Override
    public void map(List<QuestionnaireResponse.QuestionnaireResponseItemComponent> item) {
        for (QuestionnaireResponse.QuestionnaireResponseItemComponent question : item) {
            if (getValueCode(question).isPresent()) {
                mapSymptomsQuestions(question);
            }

        }

    }

    private void mapSymptomsQuestions(QuestionnaireResponse.QuestionnaireResponseItemComponent question) {
        switch (question.getLinkId()) {
            case S0:
                //TODO Strategy Pattern
                setProblemDiagnoseEvaluationIfNotSet();
                this.mapFever24h(getQuestionLoincYesNoToBoolean(question));
                break;
            case S1:
                setProblemDiagnoseEvaluationIfNotSet();
                this.mapFever4days(getQuestionLoincYesNoToBoolean(question));
                break;
            case S3:
                setProblemDiagnoseEvaluationIfNotSet();
                this.mapChills(getQuestionLoincYesNoToBoolean(question));
                break;
            case S4:
                setProblemDiagnoseEvaluationIfNotSet();
                this.mapTired(getQuestionLoincYesNoToBoolean(question));
                break;
            case S5:
                setProblemDiagnoseEvaluationIfNotSet();
                this.mapBodyAches(getQuestionLoincYesNoToBoolean(question));
                break;
            case S6:
                setProblemDiagnoseEvaluationIfNotSet();
                this.mapPersistentCoughing(getQuestionLoincYesNoToBoolean(question));
                break;
            case S7:
                setProblemDiagnoseEvaluationIfNotSet();
                this.mapRhinitis(getQuestionLoincYesNoToBoolean(question));
                break;
            case S8:
                setProblemDiagnoseEvaluationIfNotSet();
                this.mapDiarrhea(getQuestionLoincYesNoToBoolean(question));
                break;
            case S9:
                setProblemDiagnoseEvaluationIfNotSet();
                this.mapSoreThroat(getQuestionLoincYesNoToBoolean(question));
                break;
            case SA:
                setProblemDiagnoseEvaluationIfNotSet();
                this.mapHeadache(getQuestionLoincYesNoToBoolean(question));
                break;
            case SB:
                setProblemDiagnoseEvaluationIfNotSet();
                this.mapProblemsWhenBreathing(getQuestionLoincYesNoToBoolean(question));
                break;
            case SC:
                setProblemDiagnoseEvaluationIfNotSet();
                this.mapTasteSmellLoss(getQuestionLoincYesNoToBoolean(question));
                break;
            case SZ:
                setProblemDiagnoseEvaluationIfNotSet();
                this.mapWhenSymptomsAppear(getValueAsDate(question));
                break;
            default:
                throw new UnprocessableEntityException("LinkId " + question.getLinkId() + " undefined");
        }
    }

    private void setProblemDiagnoseEvaluationIfNotSet() {
        if (problemDiagnoseEvaluationQuestion.isEmpty()) {
            ProblemDiagnoseEvaluation problemDiagnoseEvaluation = new ProblemDiagnoseEvaluation();

            problemDiagnoseEvaluation.setDatumZeitpunktDesAuftretensDerErstdiagnoseValue(authored);

            problemDiagnoseEvaluation.setNameDesProblemsDerDiagnoseValue("COVID-19 Fragebogen");
            problemDiagnoseEvaluation.setLanguage(Language.DE);
            problemDiagnoseEvaluation.setSubject(new PartySelf());
            problemDiagnoseEvaluationQuestion = Optional.of(problemDiagnoseEvaluation);
        }

    }

    private void mapFever24h(Boolean hasFewer24h) {
        ProblemDiagnoseEvaluation problemDiagnoseEvaluation = problemDiagnoseEvaluationQuestion.get();
        FieberInDenLetzten24StundenCluster fieberInDenLetzten24StundenCluster = new FieberInDenLetzten24StundenCluster();
        if(hasFewer24h){
            fieberInDenLetzten24StundenCluster.setSchweregradDefiningCode(SchweregradDefiningCode.N39_C);
        }else {
            fieberInDenLetzten24StundenCluster.setSchweregradDefiningCode(SchweregradDefiningCode.N38_C);
        }
        problemDiagnoseEvaluation.setFieberInDenLetzten24Stunden(fieberInDenLetzten24StundenCluster);
        problemDiagnoseEvaluationQuestion = Optional.of(problemDiagnoseEvaluation);
    }


    private void mapFever4days(Boolean hasFewer4Days) {
        ProblemDiagnoseEvaluation problemDiagnoseEvaluation = problemDiagnoseEvaluationQuestion.get();
        FieberInDenLetzten4TagenCluster fieberInDenLetzten4TagenCluster = new FieberInDenLetzten4TagenCluster();
        if(hasFewer4Days) {
            fieberInDenLetzten4TagenCluster.setSchweregradDefiningCode(SchweregradDefiningCode.N39_C);
        }else {
            fieberInDenLetzten4TagenCluster.setSchweregradDefiningCode(SchweregradDefiningCode.N38_C);
        }
        problemDiagnoseEvaluation.setFieberInDenLetzten4Tagen(fieberInDenLetzten4TagenCluster);
        problemDiagnoseEvaluationQuestion = Optional.of(problemDiagnoseEvaluation);
    }

    private void mapChills(Boolean hasChills24h) {
        ProblemDiagnoseEvaluation problemDiagnoseEvaluation = problemDiagnoseEvaluationQuestion.get();
        SchuettelfrostInDenLetzten24StundenCluster schuttelfrostInDenLetzten24StundenCluster = new SchuettelfrostInDenLetzten24StundenCluster();
        schuttelfrostInDenLetzten24StundenCluster.setVorhandenValue(hasChills24h);
        problemDiagnoseEvaluation.setSchuettelfrostInDenLetzten24Stunden(schuttelfrostInDenLetzten24StundenCluster);
        problemDiagnoseEvaluationQuestion = Optional.of(problemDiagnoseEvaluation);

    }

    private void mapTired(Boolean isTired) {
        ProblemDiagnoseEvaluation problemDiagnoseEvaluation = problemDiagnoseEvaluationQuestion.get();
        SchlappheitAngeschlagenheitCluster schlappheitAngeschlagenheitCluster = new SchlappheitAngeschlagenheitCluster();
        schlappheitAngeschlagenheitCluster.setVorhandenValue(isTired);
        problemDiagnoseEvaluation.setSchlappheitAngeschlagenheit(schlappheitAngeschlagenheitCluster);
        problemDiagnoseEvaluationQuestion = Optional.of(problemDiagnoseEvaluation);
    }

    private void mapBodyAches(Boolean hasBodyAches) {
        ProblemDiagnoseEvaluation problemDiagnoseEvaluation = problemDiagnoseEvaluationQuestion.get();
        GliederschmerzenCluster gliederschmerzenCluster = new GliederschmerzenCluster();
        gliederschmerzenCluster.setVorhandenValue(hasBodyAches);
        problemDiagnoseEvaluation.setGliederschmerzen(gliederschmerzenCluster);
        problemDiagnoseEvaluationQuestion = Optional.of(problemDiagnoseEvaluation);
    }

    private void mapPersistentCoughing(Boolean hasPersistentCoughing24h) {
        ProblemDiagnoseEvaluation problemDiagnoseEvaluation = problemDiagnoseEvaluationQuestion.get();
        HustenInDenLetzten24StundenCluster hustenInDenLetzten24StundenCluster = new HustenInDenLetzten24StundenCluster();
        hustenInDenLetzten24StundenCluster.setVorhandenValue(hasPersistentCoughing24h);
        problemDiagnoseEvaluation.setHustenInDenLetzten24Stunden(hustenInDenLetzten24StundenCluster);
        problemDiagnoseEvaluationQuestion = Optional.of(problemDiagnoseEvaluation);
    }

    private void mapRhinitis(Boolean hasRhinitis24h) {
        ProblemDiagnoseEvaluation problemDiagnoseEvaluation = problemDiagnoseEvaluationQuestion.get();
        SchnupfenInDenLetzten24StundenCluster schnupfenInDenLetzten24StundenCluster = new SchnupfenInDenLetzten24StundenCluster();
        schnupfenInDenLetzten24StundenCluster.setVorhandenValue(hasRhinitis24h);
        problemDiagnoseEvaluation.setSchnupfenInDenLetzten24Stunden(schnupfenInDenLetzten24StundenCluster);
        problemDiagnoseEvaluationQuestion = Optional.of(problemDiagnoseEvaluation);

    }

    private void mapDiarrhea(Boolean hasDiarrhea) {
        ProblemDiagnoseEvaluation problemDiagnoseEvaluation = problemDiagnoseEvaluationQuestion.get();
        DurchfallCluster durchfallCluster = new DurchfallCluster();
        durchfallCluster.setVorhandenValue(hasDiarrhea);
        problemDiagnoseEvaluation.setDurchfall(durchfallCluster);
        problemDiagnoseEvaluationQuestion = Optional.of(problemDiagnoseEvaluation);

    }

    private void mapSoreThroat(Boolean hasSoreThroat24h) {
        ProblemDiagnoseEvaluation problemDiagnoseEvaluation = problemDiagnoseEvaluationQuestion.get();
        HalsschmerzenInDenLetzten24StundenCluster halsschmerzenInDenLetzten24StundenCluster = new HalsschmerzenInDenLetzten24StundenCluster();
        halsschmerzenInDenLetzten24StundenCluster.setVorhandenValue(hasSoreThroat24h);
        problemDiagnoseEvaluation.setHalsschmerzenInDenLetzten24Stunden(halsschmerzenInDenLetzten24StundenCluster);
        problemDiagnoseEvaluationQuestion = Optional.of(problemDiagnoseEvaluation);
    }

    private void mapHeadache(Boolean hasHeadache24h) {
        ProblemDiagnoseEvaluation problemDiagnoseEvaluation = problemDiagnoseEvaluationQuestion.get();
        KopfschmerzenCluster kopfschmerzenCluster = new KopfschmerzenCluster();
        kopfschmerzenCluster.setVorhandenValue(hasHeadache24h);
        problemDiagnoseEvaluation.setKopfschmerzen(kopfschmerzenCluster);
        problemDiagnoseEvaluationQuestion = Optional.of(problemDiagnoseEvaluation);
    }

    private void mapProblemsWhenBreathing(Boolean hasProblemsBreathing) {
        ProblemDiagnoseEvaluation problemDiagnoseEvaluation = problemDiagnoseEvaluationQuestion.get();
        AtemproblemeCluster atemproblemeCluster = new AtemproblemeCluster();
        atemproblemeCluster.setVorhandenValue(hasProblemsBreathing);
        problemDiagnoseEvaluation.setAtemprobleme(atemproblemeCluster);
        problemDiagnoseEvaluationQuestion = Optional.of(problemDiagnoseEvaluation);
    }

    private void mapTasteSmellLoss(Boolean hasTasteSmellLoss) {
        ProblemDiagnoseEvaluation problemDiagnoseEvaluation = problemDiagnoseEvaluationQuestion.get();
        GeschmacksUndOderGeruchsverlustCluster geschmacksUndOderGeruchsverlustCluster = new GeschmacksUndOderGeruchsverlustCluster();
        geschmacksUndOderGeruchsverlustCluster.setVorhandenValue(hasTasteSmellLoss);
        problemDiagnoseEvaluation.setGeschmacksUndOderGeruchsverlust(geschmacksUndOderGeruchsverlustCluster);
        problemDiagnoseEvaluationQuestion = Optional.of(problemDiagnoseEvaluation);
    }

    private void mapWhenSymptomsAppear(TemporalAccessor sinceWhenSymptoms) {
        ProblemDiagnoseEvaluation problemDiagnoseEvaluation = problemDiagnoseEvaluationQuestion.get();
        problemDiagnoseEvaluation.setDatumZeitpunktDesAuftretensDerErstdiagnoseValue(sinceWhenSymptoms);
        problemDiagnoseEvaluationQuestion = Optional.of(problemDiagnoseEvaluation);
    }

    @Override
    public List<ProblemDiagnoseEvaluation> toComposition() {
        List<ProblemDiagnoseEvaluation> problemDiagnoseEvaluationList = new ArrayList<>();
        problemDiagnoseEvaluationQuestion.ifPresent(problemDiagnoseEvaluationList::add);
        return problemDiagnoseEvaluationList;
    }
}