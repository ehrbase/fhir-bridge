package org.ehrbase.fhirbridge.ehr.converter.specific.d4lquestionnaire.sections.generalinformation;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.converter.generic.QuestionnaireResponseItemToEntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.PflegetaetigkeitEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.PflegetaetigkeitGrundFuerDieTaetigkeitElement;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

import java.util.List;
import java.util.Optional;

public class PflegetaetigkeitEvaluationConverter extends QuestionnaireResponseItemToEntryEntityConverter<PflegetaetigkeitEvaluation> {
    PflegetaetigkeitEvaluation pflegetatigkeitEvaluation = new PflegetaetigkeitEvaluation();

    @Override
    protected PflegetaetigkeitEvaluation convertInternal(QuestionnaireResponse.QuestionnaireResponseItemComponent questionnaireResponseItemComponent) {
        Boolean isPrivateCaregiver = getQuestionLoincYesNoToBoolean(questionnaireResponseItemComponent);
        pflegetatigkeitEvaluation.setLanguage(Language.DE);
        pflegetatigkeitEvaluation.setSubject(new PartySelf());

        pflegetatigkeitEvaluation.setAnzahlDerGepflegtenPersonenValue("Mindestens eine Person");
        pflegetatigkeitEvaluation.setFrequenzDerPflegeValue("Mindestens einmal die Woche");

        PflegetaetigkeitGrundFuerDieTaetigkeitElement pflegetatigkeitGrundFurDieTatigkeitElement = new PflegetaetigkeitGrundFuerDieTaetigkeitElement();
        pflegetatigkeitGrundFurDieTatigkeitElement.setValue("alterbedingten Beschwerden, chronischen Erkrankungen oder Gebrechlichkeit");

        pflegetatigkeitEvaluation.setGrundFuerDieTaetigkeit(List.of(pflegetatigkeitGrundFurDieTatigkeitElement));
        pflegetatigkeitEvaluation.setPrivatValue(isPrivateCaregiver);
        return pflegetatigkeitEvaluation;
   }
}
