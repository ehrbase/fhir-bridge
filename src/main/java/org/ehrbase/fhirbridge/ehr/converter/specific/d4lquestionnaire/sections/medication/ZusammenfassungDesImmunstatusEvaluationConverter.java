package org.ehrbase.fhirbridge.ehr.converter.specific.d4lquestionnaire.sections.medication;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.converter.generic.QuestionnaireResponseItemToEntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.AelterOderGleich65JahreAltDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.ImmunsstatusDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.ZusammenfassungDesImmunstatusEvaluation;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

import java.util.Optional;

public class ZusammenfassungDesImmunstatusEvaluationConverter extends QuestionnaireResponseItemToEntryEntityConverter<ZusammenfassungDesImmunstatusEvaluation> {
    @Override
    protected ZusammenfassungDesImmunstatusEvaluation convertInternal(QuestionnaireResponse.QuestionnaireResponseItemComponent questionnaireResponseItemComponent) {
        ZusammenfassungDesImmunstatusEvaluation zusammenfassungDesImmunstatusEvaluation = new ZusammenfassungDesImmunstatusEvaluation();
        zusammenfassungDesImmunstatusEvaluation.setInfektionskrankheitOderErregerValue("Grippe");
        zusammenfassungDesImmunstatusEvaluation.setLanguage(Language.DE);
        zusammenfassungDesImmunstatusEvaluation.setSubject(new PartySelf());

        if (getQuestionLoincYesNoToBoolean(questionnaireResponseItemComponent)) {
            zusammenfassungDesImmunstatusEvaluation.setHabenSieSichImZeitraumVom1Oktober2019BisHeuteGegenGrippeImpfenLassenDefiningCode(AelterOderGleich65JahreAltDefiningCode.JA);
            zusammenfassungDesImmunstatusEvaluation.setImmunsstatusDefiningCode(ImmunsstatusDefiningCode.IMPFSTATUS_IST_AKUTELL);
        } else {
            zusammenfassungDesImmunstatusEvaluation.setHabenSieSichImZeitraumVom1Oktober2019BisHeuteGegenGrippeImpfenLassenDefiningCode(AelterOderGleich65JahreAltDefiningCode.NEIN);
            zusammenfassungDesImmunstatusEvaluation.setImmunsstatusDefiningCode(ImmunsstatusDefiningCode.IMPFSTATUS_IST_NICHT_AKUTELL);
        }
        return zusammenfassungDesImmunstatusEvaluation;
    }
}
