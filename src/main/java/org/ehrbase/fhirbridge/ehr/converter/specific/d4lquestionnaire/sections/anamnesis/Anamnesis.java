package org.ehrbase.fhirbridge.ehr.converter.specific.d4lquestionnaire.sections.anamnesis;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.converter.specific.d4lquestionnaire.sections.QuestionnaireSection;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.D4LQuestionnaireComposition;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.AdipositasEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.ChronischeLungenkrankheitEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.DiabetesEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.HerzerkrankungEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.VorhandenDefiningCode;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import java.util.Optional;

public class Anamnesis extends QuestionnaireSection {
    private static final String D0 = "D0";
    private static final String D1 = "D1";
    private static final String D2 = "D2";
    private static final String D3 = "D3";

    private Optional<ChronischeLungenkrankheitEvaluation> chronischeLungenkrankheitEvaluationQuestion = Optional.empty();
    private Optional<DiabetesEvaluation> diabetesEvaluationQuestion = Optional.empty();
    private Optional<HerzerkrankungEvaluation> herzerkrankungEvaluationQuestion = Optional.empty();
    private Optional<AdipositasEvaluation> adipositasEvaluationQuestion = Optional.empty();

    public Anamnesis(Language language) {
        super(language);
    }

    @Override
    public void map(List<QuestionnaireResponse.QuestionnaireResponseItemComponent> item) {
        for (QuestionnaireResponse.QuestionnaireResponseItemComponent question : item) {
            if (getValueCode(question).isPresent()) {
                extractAnamnesis(question);
            }
        }
    }

    private void extractAnamnesis(QuestionnaireResponse.QuestionnaireResponseItemComponent question) {
            switch (question.getLinkId()) {
                case D0:
                    chronischeLungenkrankheitEvaluationQuestion = Optional.of(new ChronischeLungenkrankheitEvaluationConverter().convert(question, language));
                    break;
                case D1:
                    diabetesEvaluationQuestion = Optional.of(new DiabetesEvaluationConverter().convert(question, language));
                    break;
                case D2:
                    herzerkrankungEvaluationQuestion = Optional.of(new HerzerkrankungEvaluationConverter().convert(question, language));
                    break;
                case D3:
                    adipositasEvaluationQuestion = Optional.of(new AdipositasEvaluationConverter().convert(question, language));
                    break;
                default:
                    throw new UnprocessableEntityException("LinkId " + question.getLinkId() + " undefined");
            }
    }

    public void setVorUndGrunderkrankungen(D4LQuestionnaireComposition d4LQuestionnaireComposition, Language language) {
        chronischeLungenkrankheitEvaluationQuestion.ifPresent(d4LQuestionnaireComposition::setChronischeLungenkrankheit);
        diabetesEvaluationQuestion.ifPresent(d4LQuestionnaireComposition::setDiabetes);
        herzerkrankungEvaluationQuestion.ifPresent(d4LQuestionnaireComposition::setHerzerkrankung);
        adipositasEvaluationQuestion.ifPresent(d4LQuestionnaireComposition::setAdipositas);
    }
}

