package org.ehrbase.fhirbridge.fhir.bundle;

import ca.uhn.fhir.rest.gclient.ITransactionTyped;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.bloodgas.BloodGasPanelCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.radiologischerBefund.RadiologischerBefundConverter;
import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.BefundDerBlutgasanalyseComposition;
import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.definition.*;
import org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.GECCORadiologischerBefundComposition;
import org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.definition.BildgebendesUntersuchungsergebnisObservation;
import org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.definition.RadiologischerBefundKategorieElement;
import org.ehrbase.fhirbridge.fhir.AbstractBundleMappingTestSetupIT;
import org.ehrbase.fhirbridge.fhir.bundle.converter.BloodGasPanelConverter;
import org.ehrbase.fhirbridge.fhir.bundle.validator.BloodGasPanelBundleValidator;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.Observation;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.metamodel.clazz.ValueObjectDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.temporal.TemporalAccessor;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Integration tests for {@link org.hl7.fhir.r4.model.Bundle Bundle} resource.
 */
public class BundleIT extends AbstractBundleMappingTestSetupIT {


    public BundleIT() {
        super("Bundle/", Bundle.class);
    }

    @Test
    public void createBloodGas() throws IOException {
        create("create-blood-gas.json");
    }


    @Test
    void createInvalidDuplicatedBloodGasPanel() throws IOException {
        Exception exception = executeValidatorException("create-blood-gas-invalid-duplicated-panel.json");
        assertEquals("More then one Observation has the hasMember Attribute, this is not supported by the Fhir-bridge", exception.getMessage());
    }


    @Test
    void createInvalidUrn() throws IOException {
        Exception exception = executeValidatorException("create-blood-gas-invalid-urn.json");
        assertEquals("urn:uid:04121321-4af5-424c-a0e1-ed3aab1c349d is not a valid format. The fullUrl has to be an UUID and start with: urn:uuid:, example urn:uuid:04121321-4af5-424c-a0e1-ed3aab1c349d", exception.getMessage());
    }

    @Test
    void createInvalidReference() throws IOException {
        Exception exception = executeValidatorException("create-blood-gas-invalid-reference.json");
        assertEquals("The urls of the entries within the Bundle are not matching the hasMember.references. Make sure that every Url contained in the observation with hasMembers is contained and correct.", exception.getMessage());
    }

    /*HAPI FHir Bug
  @Test
    void createInvalidMember() throws IOException {
        Exception exception = executeIntegrationTestWithException("create-blood-gas-invalid-member.json");
        assertEquals("The SNOMED code: asdasd, is not supported for radiology report !", exception.getMessage());
    }*/

    @Test
    void createMappingBloodGas() throws IOException{
        Bundle bundle = (Bundle) super.testFileLoader.loadResource("create-blood-gas.json");
        BloodGasPanelConverter bloodGasPanelConverter = new BloodGasPanelConverter();
        Observation observation = bloodGasPanelConverter.convert(bundle);
        BloodGasPanelCompositionConverter bloodGasPanelCompositionConverter = new BloodGasPanelCompositionConverter();
        BefundDerBlutgasanalyseComposition mappedBefundDerBlutgasanalyseComposition = bloodGasPanelCompositionConverter.toComposition(observation);
        Diff diff = compareCompositions(getJavers(), "paragon-create-blood-gas.json", mappedBefundDerBlutgasanalyseComposition);
        assertEquals(diff.getChanges().size(), 0);
    }

    @Test
    void createInvalidRequest() throws IOException {
        Exception exception = executeValidatorException("create-blood-gas-invalid-request.json");
        assertEquals("Only the request method POST is supported !", exception.getMessage());
    }

    @Test
    void createInvalidRequestUrl() throws IOException {
        Exception exception = executeValidatorException("create-blood-gas-invalid-request-url.json");
        assertEquals("The request.url and the resourceType of the entry need to be equal!", exception.getMessage());
    }

    @Test
    void createInvalidProfile() throws IOException {
        Exception exception = executeValidatorException("create-blood-gas-invalid-profile.json");
        assertEquals("Blood gas panel bundle needs to contain only the profiles for the blood gas panel. Please delete profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/inhaled-oxygen-concentration from the Bundle.", exception.getMessage());
    }

    @Test
    void createInvalidSubjectId() throws IOException {
        Exception exception = executeValidatorException("create-bloodgas-invalid-subject-ids.json");
        assertEquals("subject.reference ids all have to be equal! A Fhir Bridge Bundle cannot reference to different Patients !", exception.getMessage());
        }

    @Test
    void createInvalidWithPanelMissing() throws IOException {
        Exception exception = executeMappingException("create-blood-gas-invalid-with-panel-missing.json");
        assertEquals("Root resource with profile 'https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-gas-panel' is missing", exception.getMessage());
    }

    @Test
    void createInvalidSubjectIdFormat() throws IOException {
        Exception exception = executeValidatorException("create-blood-gas-invalid-subject-format.json");
        assertEquals("Ensure that the subject id has the following format :         " +
                "\"subject\": {\n" +
                "          \"identifier\": {\n" +
                "            \"system\": \"urn:ietf:rfc:4122\",\n" +
                "            \"value\": \"example\"\n" +
                "          }\n" +
                "        },", exception.getMessage());
    }

    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(BefundDerBlutgasanalyseComposition.class, List.of("location")))
                .registerValueObject(LaborergebnisObservation.class)
                .registerValueObject(PhWertCluster.class)
                .registerValueObject(SauerstoffpartialdruckCluster.class)
                .registerValueObject(KohlendioxidpartialdruckCluster.class)
                .registerValueObject(SauerstoffsattigungCluster.class)
                .build();
    }

    @Override
    public Exception executeMappingException(String path) throws IOException {
        Bundle bundle = (Bundle) testFileLoader.loadResource(path);
        return assertThrows(Exception.class, () -> {
            new BloodGasPanelCompositionConverter().toComposition(new BloodGasPanelConverter().convert( bundle));
        });
    }

    public Exception executeValidatorException(String path) throws IOException {
        Bundle bundle = (Bundle) testFileLoader.loadResource(path);
        return assertThrows(Exception.class, () -> {
            new BloodGasPanelBundleValidator().validateRequest(bundle, null);
        });
    }

}

