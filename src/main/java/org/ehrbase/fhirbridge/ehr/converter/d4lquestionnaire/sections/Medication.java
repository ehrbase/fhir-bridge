package org.ehrbase.fhirbridge.ehr.converter.d4lquestionnaire.sections;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.D4LQuestionnaireComposition;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.AelterOderGleich65JahreAltDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.ImmunsstatusDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.ImmunsuppressivaEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.KortisionEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.StatusDefiningCode2;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.ZusammenfassungDesImmunstatusEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.ZusammenfassungDesImmunstatusInfektionskrankheitOderErregerElement;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

import java.time.temporal.TemporalAccessor;
import java.util.List;
import java.util.Optional;

public class Medication extends QuestionnaireSection {

    private static final String M0 = "M0";
    private static final String M1 = "M1";
    private static final String M2 = "M2";


    private Optional<ZusammenfassungDesImmunstatusEvaluation> zusammenfassungDesImmunstatusEvaluationQuestion = Optional.empty();
    private Optional<ImmunsuppressivaEvaluation> immunsuppressivaEvaluationQuestion = Optional.empty();
    private Optional<KortisionEvaluation> kortisonEvaluationQuestion = Optional.empty();

    public Medication(TemporalAccessor authored) {
        super(authored);
    }


    @Override
    public void map(List<QuestionnaireResponse.QuestionnaireResponseItemComponent> item) {
        for (QuestionnaireResponse.QuestionnaireResponseItemComponent question : item) {
            if (getValueCode(question).isPresent()) {
                extractMedication(question);
            }
        }
    }

    private void extractMedication(QuestionnaireResponse.QuestionnaireResponseItemComponent question) {
        switch (question.getLinkId()) {
            case M0:
                this.mapSteroids(getQuestionValueCodeToString(question));
                break;
            case M1:
                this.mapImmunosuppressants(getQuestionValueCodeToString(question));
                break;
            case M2:
                this.mapVaccinatedFlu(getQuestionLoincYesNoToBoolean(question));
                break;
            default:
                throw new UnprocessableEntityException("LinkId " + question.getLinkId() + " undefined");
        }
    }

    protected void mapSteroids(String useSteroids) {
        KortisionEvaluation kortisonEvaluation = new KortisionEvaluation();
        kortisonEvaluation.setLanguage(Language.DE);
        kortisonEvaluation.setSubject(new PartySelf());
        kortisonEvaluation.setStatusDefiningCode(getStatusDefiningCode2(useSteroids));
        kortisonEvaluationQuestion = Optional.of(kortisonEvaluation);
    }

    protected void mapImmunosuppressants(String useImmunosuppressants) {
        ImmunsuppressivaEvaluation immunsuppressivaEvaluation = new ImmunsuppressivaEvaluation();
        immunsuppressivaEvaluation.setStatusDefiningCode(getStatusDefiningCode2(useImmunosuppressants));
        immunsuppressivaEvaluation.setLanguage(Language.DE);
        immunsuppressivaEvaluation.setSubject(new PartySelf());
        immunsuppressivaEvaluationQuestion = Optional.of(immunsuppressivaEvaluation);
    }

    private StatusDefiningCode2 getStatusDefiningCode2(String codeString) {
        if (codeString.equals(StatusDefiningCode2.JA.getCode())) {
            return StatusDefiningCode2.JA;
        } else if (codeString.equals(StatusDefiningCode2.NEIN.getCode())) {
            return StatusDefiningCode2.NEIN;
        } else if (codeString.equals(StatusDefiningCode2.ICH_WEISS_ES_NICHT.getCode())) {
            return StatusDefiningCode2.ICH_WEISS_ES_NICHT;
        } else {
            throw new UnprocessableEntityException("The code:" + codeString + " cannot be mapped, please enter a valid code e.g. ja (LA33-6), nein (LA32-8), ich weiss es nicht (LA12688-0)");
        }
    }


    protected void mapVaccinatedFlu(Boolean wasVaccinatedFlu) {
        ZusammenfassungDesImmunstatusEvaluation zusammenfassungDesImmunstatusEvaluation = new ZusammenfassungDesImmunstatusEvaluation();
        zusammenfassungDesImmunstatusEvaluation.setInfektionskrankheitOderErregerValue("Grippe");
        zusammenfassungDesImmunstatusEvaluation.setLanguage(Language.DE);
        zusammenfassungDesImmunstatusEvaluation.setSubject(new PartySelf());

        if(wasVaccinatedFlu){
            zusammenfassungDesImmunstatusEvaluation.setHabenSieSichImZeitraumVom1Oktober2019BisHeuteGegenGrippeImpfenLassenDefiningCode(AelterOderGleich65JahreAltDefiningCode.JA);
            zusammenfassungDesImmunstatusEvaluation.setImmunsstatusDefiningCode(ImmunsstatusDefiningCode.IMPFSTATUS_IST_AKUTELL);
        }else{
            zusammenfassungDesImmunstatusEvaluation.setHabenSieSichImZeitraumVom1Oktober2019BisHeuteGegenGrippeImpfenLassenDefiningCode(AelterOderGleich65JahreAltDefiningCode.NEIN);
            zusammenfassungDesImmunstatusEvaluation.setImmunsstatusDefiningCode(ImmunsstatusDefiningCode.IMPFSTATUS_IST_NICHT_AKUTELL);
        }
        zusammenfassungDesImmunstatusEvaluationQuestion = Optional.of(zusammenfassungDesImmunstatusEvaluation);
    }

    public void setMedikamenteImpfungen(D4LQuestionnaireComposition d4LQuestionnaireComposition) {
        zusammenfassungDesImmunstatusEvaluationQuestion.ifPresent(d4LQuestionnaireComposition::setZusammenfassungDesImmunstatus);
        immunsuppressivaEvaluationQuestion.ifPresent(d4LQuestionnaireComposition::setImmunsuppressiva);
        kortisonEvaluationQuestion.ifPresent(d4LQuestionnaireComposition::setKortision);
    }
}
