package org.ehrbase.fhirbridge.fhir;

import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.api.MethodOutcome;
import com.nedap.archie.rm.generic.PartySelf;
import org.apache.commons.io.IOUtils;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.fhirbridge.ehr.converter.d4lquestionnaire.D4lQuestionnaireCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.D4LQuestionnaireComposition;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.*;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Change;
import org.javers.core.diff.Diff;
import org.javers.core.metamodel.clazz.EntityDefinitionBuilder;
import org.javers.core.metamodel.clazz.ValueObjectDefinition;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.javers.core.diff.ListCompareAlgorithm.LEVENSHTEIN_DISTANCE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Integration tests for {@link org.hl7.fhir.r4.model.QuestionnaireResponse QuestionnaireResponse} resource.
 */
class QuestionnaireResponseIT extends AbstractSetupIT {

    @Test
    void createCovApp() throws IOException {
        String resource = IOUtils.toString(new ClassPathResource("QuestionnaireResponse/create-covapp-response.json").getInputStream(), StandardCharsets.UTF_8);
        MethodOutcome outcome = client.create().resource(resource.replaceAll(PATIENT_ID_TOKEN, PATIENT_ID)).execute();

        assertNotNull(outcome.getId());
        assertEquals(true, outcome.getCreated());
    }


/*    @Test
    void createInvalid() throws IOException {
        String resource = IOUtils.toString(new ClassPathResource("QuestionnaireResponse/create-invalid.json").getInputStream(), StandardCharsets.UTF_8);
        ICreateTyped createTyped = client.create().resource(resource.replaceAll(PATIENT_ID_TOKEN, PATIENT_ID));
        Exception exception = Assertions.assertThrows(UnprocessableEntityException.class, createTyped::execute);

        assertEquals("HTTP 422 : QuestionnaireResponse.status: minimum required = 1, but only found 0 (from http://hl7.org/fhir/StructureDefinition/QuestionnaireResponse)", exception.getMessage());
    }*/

    @Test
    void mapD4LQuestionnaireComposition() throws IOException {
        String resource = IOUtils.toString(new ClassPathResource("QuestionnaireResponse/create-covapp-response.json").getInputStream(), StandardCharsets.UTF_8);
        Javers javers = getJavers();

        IParser parser = context.newJsonParser();
        QuestionnaireResponse questionnaireResponse = parser.parseResource(QuestionnaireResponse.class, resource);
        D4lQuestionnaireCompositionConverter d4lQuestionnaireCompositionConverter = new D4lQuestionnaireCompositionConverter();
        D4LQuestionnaireComposition mappedD4LQuestionnaireComposition = d4lQuestionnaireCompositionConverter.toComposition(questionnaireResponse);

        D4LQuestionnaireComposition d4LQuestionnaireCompositionCorrect = createD4LQuestionaire(questionnaireResponse);
        d4LQuestionnaireCompositionCorrect.setVersionUid(new VersionUid("0cc98096-c6de-4670-865f-f4f2aae48128"));

        Diff diff = javers.compare(d4LQuestionnaireCompositionCorrect.getProblemDiagnose().get(0), mappedD4LQuestionnaireComposition.getProblemDiagnose().get(0));
        diff.getChanges().forEach(change -> System.out.println("Difference at" + change));

        assertEquals(diff.getChanges().size(), 0);
    }


    private Javers getJavers(){
        return JaversBuilder.javers()
                .registerValueObject(new ValueObjectDefinition(ProblemDiagnoseEvaluation.class, Arrays.asList("datumZeitpunktDesAuftretensDerErstdiagnoseValue")))
                .registerValueObject(FieberInDenLetzten24StundenCluster.class)
                .registerValueObject(FieberInDenLetzten4TagenCluster.class)
                .registerValueObject(SchuettelfrostInDenLetzten24StundenCluster.class)
                .registerValueObject(SchlappheitAngeschlagenheitCluster.class)
                .registerValueObject(GliederschmerzenCluster.class)
                .registerValueObject(HustenInDenLetzten24StundenCluster.class)
                .registerValueObject(SchnupfenInDenLetzten24StundenCluster.class)
                .registerValueObject(DurchfallCluster.class)
                .registerValueObject(HalsschmerzenInDenLetzten24StundenCluster.class)
                .registerValueObject(KopfschmerzenCluster.class)
                .registerValueObject(AtemproblemeCluster.class)
                .registerValueObject(GeschmacksUndOderGeruchsverlustCluster.class)
                .build();
    }


    private D4LQuestionnaireComposition createD4LQuestionaire(QuestionnaireResponse questionnaireResponse) {

        D4LQuestionnaireComposition d4LQuestionnaireComposition = new D4LQuestionnaireComposition();

        d4LQuestionnaireComposition.setLanguage(Language.DE);
        d4LQuestionnaireComposition.setLocation("test");
        d4LQuestionnaireComposition.setSettingDefiningCode(Setting.SECONDARY_MEDICAL_CARE);
        d4LQuestionnaireComposition.setTerritory(Territory.DE);
        d4LQuestionnaireComposition.setCategoryDefiningCode(Category.EVENT);
        d4LQuestionnaireComposition.setComposer(new PartySelf());

        TemporalAccessor authored = questionnaireResponse.getAuthoredElement().getValueAsCalendar().toZonedDateTime();
        d4LQuestionnaireComposition.setStartTimeValue(questionnaireResponse.getAuthoredElement().getValueAsCalendar().toZonedDateTime());
        d4LQuestionnaireComposition.setProblemDiagnose(getSymptoms(d4LQuestionnaireComposition, authored));


        return d4LQuestionnaireComposition;
    }

    private List<ProblemDiagnoseEvaluation> getSymptoms(D4LQuestionnaireComposition d4LQuestionnaireComposition, TemporalAccessor authored) {
        ProblemDiagnoseEvaluation problemDiagnoseEvaluation = new ProblemDiagnoseEvaluation();
        problemDiagnoseEvaluation.setDatumZeitpunktDesAuftretensDerErstdiagnoseValue(authored);
        problemDiagnoseEvaluation.setNameDesProblemsDerDiagnoseValue("COVID-19 Fragebogen");
        problemDiagnoseEvaluation.setLanguage(Language.DE);
        problemDiagnoseEvaluation.setSubject(new PartySelf());

        FieberInDenLetzten24StundenCluster fieberInDenLetzten24StundenCluster = new FieberInDenLetzten24StundenCluster();
        fieberInDenLetzten24StundenCluster.setVorhandenValue(false);
        problemDiagnoseEvaluation.setFieberInDenLetzten24Stunden(fieberInDenLetzten24StundenCluster);

        FieberInDenLetzten4TagenCluster fieberInDenLetzten4TagenCluster = new FieberInDenLetzten4TagenCluster();
        fieberInDenLetzten4TagenCluster.setVorhandenValue(true);
        fieberInDenLetzten4TagenCluster.setSchweregradDefiningCode(SchweregradDefiningCode.N40_C);
        problemDiagnoseEvaluation.setFieberInDenLetzten4Tagen(fieberInDenLetzten4TagenCluster);

        SchuettelfrostInDenLetzten24StundenCluster schuttelfrostInDenLetzten24StundenCluster = new SchuettelfrostInDenLetzten24StundenCluster();
        schuttelfrostInDenLetzten24StundenCluster.setVorhandenValue(false);
        problemDiagnoseEvaluation.setSchuettelfrostInDenLetzten24Stunden(schuttelfrostInDenLetzten24StundenCluster);

        SchlappheitAngeschlagenheitCluster schlappheitAngeschlagenheitCluster = new SchlappheitAngeschlagenheitCluster();
        schlappheitAngeschlagenheitCluster.setVorhandenValue(true);
        problemDiagnoseEvaluation.setSchlappheitAngeschlagenheit(schlappheitAngeschlagenheitCluster);

        GliederschmerzenCluster gliederschmerzenCluster = new GliederschmerzenCluster();
        gliederschmerzenCluster.setVorhandenValue(false);
        problemDiagnoseEvaluation.setGliederschmerzen(gliederschmerzenCluster);

        HustenInDenLetzten24StundenCluster hustenInDenLetzten24StundenCluster = new HustenInDenLetzten24StundenCluster();
        hustenInDenLetzten24StundenCluster.setVorhandenValue(true);
        problemDiagnoseEvaluation.setHustenInDenLetzten24Stunden(hustenInDenLetzten24StundenCluster);

        SchnupfenInDenLetzten24StundenCluster schnupfenInDenLetzten24StundenCluster = new SchnupfenInDenLetzten24StundenCluster();
        schnupfenInDenLetzten24StundenCluster.setVorhandenValue(false);
        problemDiagnoseEvaluation.setSchnupfenInDenLetzten24Stunden(schnupfenInDenLetzten24StundenCluster);

        DurchfallCluster durchfallCluster = new DurchfallCluster();
        durchfallCluster.setVorhandenValue(false);
        problemDiagnoseEvaluation.setDurchfall(durchfallCluster);

        HalsschmerzenInDenLetzten24StundenCluster halsschmerzenInDenLetzten24StundenCluster = new HalsschmerzenInDenLetzten24StundenCluster();
        halsschmerzenInDenLetzten24StundenCluster.setVorhandenValue(true);
        problemDiagnoseEvaluation.setHalsschmerzenInDenLetzten24Stunden(halsschmerzenInDenLetzten24StundenCluster);

        KopfschmerzenCluster kopfschmerzenCluster = new KopfschmerzenCluster();
        kopfschmerzenCluster.setVorhandenValue(false);
        problemDiagnoseEvaluation.setKopfschmerzen(kopfschmerzenCluster);

        AtemproblemeCluster atemproblemeCluster = new AtemproblemeCluster();
        atemproblemeCluster.setVorhandenValue(false);
        problemDiagnoseEvaluation.setAtemprobleme(atemproblemeCluster);

        GeschmacksUndOderGeruchsverlustCluster geschmacksUndOderGeruchsverlustCluster = new GeschmacksUndOderGeruchsverlustCluster();
        geschmacksUndOderGeruchsverlustCluster.setVorhandenValue(false);
        problemDiagnoseEvaluation.setGeschmacksUndOderGeruchsverlust(geschmacksUndOderGeruchsverlustCluster);

        problemDiagnoseEvaluation.setDatumZeitpunktDesAuftretensDerErstdiagnoseValue(LocalDate.parse("2020-03-30"));

        return List.of(problemDiagnoseEvaluation);
    }
}
