package org.ehrbase.fhirbridge.fhir.observation;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.specific.virologischerBefund.PCRCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccovirologischerbefundcomposition.GECCOVirologischerBefundComposition;
import org.ehrbase.fhirbridge.ehr.opt.geccovirologischerbefundcomposition.definition.*;
import org.ehrbase.fhirbridge.fhir.AbstractMappingTestSetupIT;
import org.hl7.fhir.r4.model.Observation;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.metamodel.clazz.ValueObjectDefinition;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.temporal.TemporalAccessor;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PCRIT extends AbstractMappingTestSetupIT {

    public PCRIT() {
        super("Observation/PCR/", Observation.class); //fhir-Resource
    }

    @Test
    void createPCRDetected() throws IOException {
        create("create-PCR-48.json");
    }

    @Test
    void createPCRInconclusive() throws IOException {
        create("create-PCR-49.json");
    }

    @Test
    void createPCRNotDetected() throws IOException {
        create("create-PCR-50.json");
    }


    // #####################################################################################
    // check payload
    @Test
    void mappingNormal() throws IOException {
        testMapping("create-PCR-50.json",
                "paragon-PCR.json");
    }

    // #####################################################################################
    // check exceptions
    @Test
    void createInvalidValueCode() throws IOException {
        Exception exception = executeMappingException("create-PCR-value_invalid.json");
        assertEquals("Value code 404 is not supported", exception.getMessage());
    }

    @Test
    void createInvalidStatuseCode() throws IOException {
        Exception exception = executeMappingException("create-PCR-status_invalid.json");
        assertEquals("createStatusDefiningCode failed. Code not found for: UNKNOWN", exception.getMessage());
    }


    // #####################################################################################
    // default

    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(GECCOVirologischerBefundComposition.class, List.of("location",  "feederAudit")))
                .registerValueObject(BefundObservation.class)
                .registerValueObject(GeccoVirologischerBefundKategorieLoincElement.class)
                .registerValueObject(KategorieDefiningCode.class)
                .registerValueObject(KategorieLoincDefiningCode.class)
                .registerValueObject(LabortestBezeichnungDefiningCode.class)
                .registerValueObject(LabortestPanelCluster.class)
                .registerValueObject(NachweisDefiningCode.class)
                .registerValueObject(ProAnalytCluster.class)
                .registerValueObject(StatusDefiningCode.class)
                .registerValueObject(VirusnachweistestDefiningCode.class)
                .build();
    }

    @Override
    public Exception executeMappingException(String path) throws IOException {
        Observation obs = (Observation) testFileLoader.loadResource(path);
        return assertThrows(UnprocessableEntityException.class, () ->
            new PCRCompositionConverter().convert(obs)
        );
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        Observation observation = (Observation)  super.testFileLoader.loadResource(resourcePath);
        PCRCompositionConverter pcrCompositionConverter = new PCRCompositionConverter();
        GECCOVirologischerBefundComposition mapped = pcrCompositionConverter.convert(observation);
        Diff diff = compareCompositions(getJavers(), paragonPath, mapped);
        assertEquals(0, diff.getChanges().size());
    }
}
