package org.ehrbase.fhirbridge.fhir.questionnaireresponse;

import ca.uhn.fhir.rest.gclient.ICreateTyped;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.fhirbridge.ehr.converter.d4lquestionnaire.D4lQuestionnaireCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.D4LQuestionnaireComposition;
import org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition.*;
import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.fhir.AbstractMappingTestSetupIT;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.metamodel.clazz.ValueObjectDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.temporal.TemporalAccessor;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for {@link org.hl7.fhir.r4.model.QuestionnaireResponse QuestionnaireResponse} resource.
 */
class QuestionnaireResponseIT extends AbstractMappingTestSetupIT {

    public QuestionnaireResponseIT() {
        super("QuestionnaireResponse/", QuestionnaireResponse.class);
    }

    @Test
    void createCovApp() throws IOException {
        create("create-covapp-response.json");
    }

    @Test
    void createInvalid() throws IOException {
        String resource =super.testFileLoader.loadResourceToString("create-invalid.json");
        ICreateTyped createTyped = client.create().resource(resource.replaceAll(PATIENT_ID_TOKEN, PATIENT_ID));
        Exception exception = Assertions.assertThrows(UnprocessableEntityException.class, createTyped::execute);

        assertEquals("HTTP 422 : QuestionnaireResponse.status: minimum required = 1, but only found 0 (from http://hl7.org/fhir/StructureDefinition/QuestionnaireResponse)", exception.getMessage());
    }

    // ------ Anamnesis ------
    @Test
    void createWithInvalidLinkIdAnamnesis() throws IOException {
        Exception exception = executeMappingException("anamnesis-invalid-linkid.json");
        assertEquals("LinkId D8 undefined", exception.getMessage());
    }

    @Test
    void createAnamnesisInvalidVorhandenerDefiningCode() throws IOException {
        Exception exception = executeMappingException("anamnesis-invalid-defining-code.json");
        assertEquals("The code LA32-123 for Question: definition cannot be mapped, please enter a valid code valid codes are: Yes (LA33-6), No (LA32-8), dont know (LA12688-0)", exception.getMessage());
    }

    // ------ General Information ------
    @Test
    void createGeneralInformationInvalidLinkId() throws IOException {
        Exception exception = executeMappingException("general-information-invalid-linkid.json");
        assertEquals("LinkId P12 undefined", exception.getMessage());
    }

    @Test
    void createGeneralInformationInvalidLinkIdKontakt() throws IOException {
        Exception exception = executeMappingException("general-information-invalid-linkid-kontakt.json");
        assertEquals("LinkId CJ undefined", exception.getMessage());
    }


    @Test
    void createGeneralInformationInvalidAge() throws IOException {
        Exception exception = executeMappingException("general-information-invalid-age.json");
        assertEquals("The code for age:30-209202 cannot be mapped, plese enter a valid code e.g. 61-70", exception.getMessage());
    }

    @Test
    void createGeneralInformationInvalidWohnungssituation() throws IOException {
        Exception exception = executeMappingException("general-information-invalid-wohnungssituation.json");
        assertEquals("The code for Wohnungsituation:LA6255213-9 cannot be mapped, please enter a valid code e.g. Wohnt mit anderen zusammen (LOINC: LA9996-5)", exception.getMessage());
    }

    @Test
    void createGeneralInformationInvalidPregnancy() throws IOException {
        Exception exception = executeMappingException("general-information-invalid-pregnancy.json");
        assertEquals("The code for Pregnancy:LA266asd83-5 cannot be mapped, please enter a valid code e.g. pregnant (LA15173-0), not pregnant (LA26683-5) or unknown(LA4489-6) )", exception.getMessage());
    }

    @Test
    void createGeneralInformationInvalidKontakt() throws IOException {
        Exception exception = executeMappingException("general-information-invalid-kontakt.json");
        assertEquals("\"LA3asd3-6\" cannot be mapped to boolean, has to be either LA33-6 or LA33-8", exception.getMessage());
    }

    // ------ Medication ------

    @Test
    void createMediactionInvalidLinkId() throws IOException {
        Exception exception = executeMappingException("medication-invalid-linkid.json");
        assertEquals("LinkId M9 undefined", exception.getMessage());
    }


    @Test
    void createMediactionInvalidSteroid() throws IOException {
        Exception exception = executeMappingException("medication-invalid-steroids.json");
        assertEquals("The code:LA3a2-8 cannot be mapped, please enter a valid code e.g. ja (LA33-6), nein (LA32-8), ich weiss es nicht (LA12688-0)", exception.getMessage());
    }

    // ------ Symptoms ------

    @Test
    void createSymptomsInvalidLinkId() throws IOException {
        Exception exception = executeMappingException("symptoms-invalid-linkid.json");
        assertEquals("LinkId Sas undefined", exception.getMessage());
    }

    @Test
    void createSymptomsInvalidSchweregrad() throws IOException {
        Exception exception = executeMappingException("symtoms-invalid-schweregrad.json");
        assertEquals("fewer max temperature: 4sa0C is not a valid code value !", exception.getMessage());
    }

    @Test
    void mapD4LQuestionnaireCompositionJavers() throws IOException, IntrospectionException, InvocationTargetException, IllegalAccessException {
       QuestionnaireResponse resource = (QuestionnaireResponse)  super.testFileLoader.loadResource("create-covapp-response.json");

        D4lQuestionnaireCompositionConverter d4lQuestionnaireCompositionConverter = new D4lQuestionnaireCompositionConverter();
        D4LQuestionnaireComposition mappedD4LQuestionnaireComposition = d4lQuestionnaireCompositionConverter.toComposition(resource);

        Diff diff = compareCompositions(getJavers(), "d4L-questionnaire-paragon-composition.json", mappedD4LQuestionnaireComposition);
        assertEquals(diff.getChanges().size(), 0);
    }

    @Override
    public Exception executeMappingException(String path) throws IOException {
        QuestionnaireResponse questionnaireResponse = (QuestionnaireResponse) testFileLoader.loadResource(path);
        return assertThrows(UnprocessableEntityException.class, () -> {
            new D4lQuestionnaireCompositionConverter().toComposition(questionnaireResponse);
        });
    }


    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(D4LQuestionnaireComposition.class, List.of("location")))
                .registerValueObject((ProblemDiagnoseEvaluation.class))
                .registerValueObject(AlterObservation.class)
                .registerValueObject(WohnsituationEvaluation.class)
                .registerValueObject(AusschlussPflegetaetigkeitEvaluation.class)
                .registerValueObject(ZusammenfassungRauchverhaltenEvaluation.class)
                .registerValueObject(SchwangerschaftsstatusObservation.class)
                .registerValueObject(PflegetaetigkeitEvaluation.class)
                .registerValueObject(KontaktAction.class)
                .registerValueObject(ChronischeLungenkrankheitEvaluation.class)
                .registerValueObject(BeschaeftigungCluster.class)
                .registerValueObject(DiabetesEvaluation.class)
                .registerValueObject(HerzerkrankungEvaluation.class)
                .registerValueObject(AdipositasEvaluation.class)
                .registerValueObject(KortisionEvaluation.class)
                .registerValueObject(ImmunsuppressivaEvaluation.class)
                .registerValueObject(ZusammenfassungDesImmunstatusEvaluation.class)
                .registerValueObject(EinwilligungserklaerungAction.class)
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

}
