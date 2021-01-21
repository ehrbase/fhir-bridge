package org.ehrbase.fhirbridge.fhir;

import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.api.MethodOutcome;
import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.composition.Composition;
import liquibase.pro.packaged.T;
import org.apache.commons.io.IOUtils;
import org.ehrbase.client.flattener.Flattener;
import org.ehrbase.fhirbridge.ehr.ResourceTemplateProvider;
import org.ehrbase.fhirbridge.ehr.converter.d4lquestionnaire.D4lQuestionnaireCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.D4LQuestionnaireComposition;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.*;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.javers.common.exception.JaversException;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.metamodel.clazz.ValueObjectDefinition;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void mapD4LQuestionnaireCompositionJavers() throws IOException, IntrospectionException, InvocationTargetException, IllegalAccessException, JSONException {
        String resource = IOUtils.toString(new ClassPathResource("QuestionnaireResponse/create-covapp-response.json").getInputStream(), StandardCharsets.UTF_8);

        RMObject composition = new CanonicalJson().unmarshal(IOUtils.toString(new ClassPathResource("QuestionnaireResponse/D4LQuestionnaire.json").getInputStream(), StandardCharsets.UTF_8), Composition.class);

        ResourceTemplateProvider resourceTemplateProvider = new ResourceTemplateProvider("classpath:/opt/");
        resourceTemplateProvider.afterPropertiesSet();

        Flattener cut = new Flattener(resourceTemplateProvider);
        D4LQuestionnaireComposition d4LQuestionnaireComposition = cut.flatten(composition, D4LQuestionnaireComposition.class);

        IParser parser = context.newJsonParser();
        QuestionnaireResponse questionnaireResponse = parser.parseResource(QuestionnaireResponse.class, resource);
        D4lQuestionnaireCompositionConverter d4lQuestionnaireCompositionConverter = new D4lQuestionnaireCompositionConverter();
        D4LQuestionnaireComposition mappedD4LQuestionnaireComposition = d4lQuestionnaireCompositionConverter.toComposition(questionnaireResponse);

        Javers javers = getJavers();
        Diff diff = javers.compare(d4LQuestionnaireComposition, mappedD4LQuestionnaireComposition);
        diff.getChanges().forEach(change -> System.out.println("Difference at" + change));
    //    assertEquals(diff.getChanges().size(), 0);

    //    assertEquals(d4LQuestionnaireComposition.getStartTimeValue(), mappedD4LQuestionnaireComposition.getStartTimeValue());
    }


    private Javers getJavers() {
        return JaversBuilder.javers()
                .registerValueObject(new ValueObjectDefinition(D4LQuestionnaireComposition.class, Collections.singletonList("startTimeValue")))
                .registerValueObject(new ValueObjectDefinition(ProblemDiagnoseEvaluation.class, Collections.singletonList("datumZeitpunktDesAuftretensDerErstdiagnoseValue")))
                .registerValueObject(new ValueObjectDefinition(AlterObservation.class, Arrays.asList("originValue", "timeValue")))
                .registerValueObject(WohnsituationEvaluation.class)
                .registerValueObject(AusschlussPflegetaetigkeitEvaluation.class)
                .registerValueObject(ZusammenfassungRauchverhaltenEvaluation.class)
                .registerValueObject(new ValueObjectDefinition(SchwangerschaftsstatusObservation.class, Arrays.asList("originValue", "timeValue")))
                .registerValueObject(PflegetaetigkeitEvaluation.class)
                .registerValueObject(new ValueObjectDefinition(KontaktAction.class, Arrays.asList("endeValue", "beginnValue", "timeValue")))
                .registerValueObject(ChronischeLungenkrankheitEvaluation.class)
                .registerValueObject(BeschaeftigungCluster.class)
                .registerValueObject(DiabetesEvaluation.class)
                .registerValueObject(HerzerkrankungEvaluation.class)
                .registerValueObject(AdipositasEvaluation.class)
                .registerValueObject(KortisionEvaluation.class)
                .registerValueObject(ImmunsuppressivaEvaluation.class)
                .registerValueObject(ZusammenfassungDesImmunstatusEvaluation.class)
                .registerValueObject(new ValueObjectDefinition(EinwilligungserklaerungAction.class, Arrays.asList("timeValue")))
                .registerValueObject(ZusammenfassungDerBeschaeftigungEvaluation.class)
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

    private void debugJaversTest(T test, T mapped) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Javers javers = getJavers();
        for (PropertyDescriptor propertyDescriptor :
                Introspector.getBeanInfo(D4LQuestionnaireComposition.class).getPropertyDescriptors()) {
            Method method = propertyDescriptor.getReadMethod();
            System.out.println(propertyDescriptor.getReadMethod());
            try {
                Diff diff = javers.compare(method.invoke(test), method.invoke(mapped));
                diff.getChanges().forEach(change -> System.out.println("Difference at" + change));
            } catch (JaversException je) {
                if (!je.toString().contains("COMPARING_TOP_LEVEL_VALUES_NOT_SUPPORTED")) {
                    throw je;
                }
            }
        }
    }


/*    @Test
    void createInvalid() throws IOException {
        String resource = IOUtils.toString(new ClassPathResource("QuestionnaireResponse/create-invalid.json").getInputStream(), StandardCharsets.UTF_8);
        ICreateTyped createTyped = client.create().resource(resource.replaceAll(PATIENT_ID_TOKEN, PATIENT_ID));
        Exception exception = Assertions.assertThrows(UnprocessableEntityException.class, createTyped::execute);

        assertEquals("HTTP 422 : QuestionnaireResponse.status: minimum required = 1, but only found 0 (from http://hl7.org/fhir/StructureDefinition/QuestionnaireResponse)", exception.getMessage());
    }*/

 /*   @Test
    void mapD4LQuestionnaireComposition() throws IOException, IntrospectionException, InvocationTargetException, IllegalAccessException, JSONException {
        String resource = IOUtils.toString(new ClassPathResource("QuestionnaireResponse/create-covapp-response.json").getInputStream(), StandardCharsets.UTF_8);

        String test = IOUtils.toString(new ClassPathResource("QuestionnaireResponse/D4LQuestionnaire.json").getInputStream(), StandardCharsets.UTF_8);

        IParser parser = context.newJsonParser();
        QuestionnaireResponse questionnaireResponse = parser.parseResource(QuestionnaireResponse.class, resource);
        D4lQuestionnaireCompositionConverter d4lQuestionnaireCompositionConverter = new D4lQuestionnaireCompositionConverter();
        D4LQuestionnaireComposition mappedD4LQuestionnaireComposition = d4lQuestionnaireCompositionConverter.toComposition(questionnaireResponse);


        ResourceTemplateProvider resourceTemplateProvider = new ResourceTemplateProvider("classpath:/opt/");
        resourceTemplateProvider.afterPropertiesSet();
        Unflattener cut = new Unflattener(resourceTemplateProvider);
        Composition composition = (Composition) cut.unflatten(mappedD4LQuestionnaireComposition);

        String s = new CanonicalJson().marshal(composition);
        ObjectMapper mapper = new ObjectMapper();

        JsonNode beforeNode = mapper.readTree(s);
        JsonNode afterNode = mapper.readTree(test);
        JsonNode patch = JsonDiff.asJson(beforeNode, afterNode);
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        Javers javers = getJavers();

        Diff diff = javers.compare(beforeNode, afterNode);
        diff.getChanges().forEach(change -> System.out.println("Difference at" + change));

        *//*
        Gson g = new Gson();
        Type mapType = new TypeToken<Map<String, Object>>(){}.getType();
        Map<String, Object> firstMap = g.fromJson(s, mapType);
        Map<String, Object> secondMap = g.fromJson(test, mapType);
        System.out.println(Maps.difference(firstMap, secondMap));
        JSONAssert.assertEquals(s, test, JSONCompareMode.NON_EXTENSIBLE);
*//*
        assertTrue(beforeNode.equals(afterNode));
    }*/
}
