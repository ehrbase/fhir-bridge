package org.ehrbase.fhirbridge.ehr.converter.specific.d4lquestionnaire.sections.medication;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.converter.specific.d4lquestionnaire.sections.QuestionnaireSection;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.D4LQuestionnaireComposition;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.AelterOderGleich65JahreAltDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.ImmunsstatusDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.ImmunsuppressivaEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.KortisionEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.StatusDefiningCode2;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.ZusammenfassungDesImmunstatusEvaluation;
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

    public Medication(Language language, TemporalAccessor startTimeValue) {
        super(language, startTimeValue);
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
                kortisonEvaluationQuestion = Optional.of(new KortisonEvaluationConverter().convert(question, language, authored));
                break;
            case M1:
                immunsuppressivaEvaluationQuestion = Optional.of(new ImmunsuppressivaEvaluationConverter().convert(question, language, authored));
                break;
            case M2:
                zusammenfassungDesImmunstatusEvaluationQuestion = Optional.of(new ZusammenfassungDesImmunstatusEvaluationConverter().convert(question, language, authored));
                break;
            default:
                throw new ConversionException("LinkId " + question.getLinkId() + " undefined");
        }
    }


    public void setMedikamenteImpfungen(D4LQuestionnaireComposition d4LQuestionnaireComposition) {
        zusammenfassungDesImmunstatusEvaluationQuestion.ifPresent(d4LQuestionnaireComposition::setZusammenfassungDesImmunstatus);
        immunsuppressivaEvaluationQuestion.ifPresent(d4LQuestionnaireComposition::setImmunsuppressiva);
        kortisonEvaluationQuestion.ifPresent(d4LQuestionnaireComposition::setKortision);
    }
}
