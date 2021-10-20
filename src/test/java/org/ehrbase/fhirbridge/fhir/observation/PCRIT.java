package org.ehrbase.fhirbridge.fhir.observation;

import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.specific.geccovirologischerbefund.PCRCompositionConverter;
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
    void createPCR() throws IOException {
        create("create-PCR.json");
    }

    // #####################################################################################
    // check payload

    @Test
    void testPcrDetected() throws IOException {
        testMapping("create-PCR-detected.json",
                "paragon-PCR-detected.json");
    }

    @Test
    void testPcrNonDetected() throws IOException {
        testMapping("create-PCR-non-detected.json",
                "paragon-PCR-non-detected.json");
    }

    @Test
    void testPcrInconclusive() throws IOException {
        testMapping("create-PCR-inconclusive.json",
                "paragon-PCR-inconclusive.json");
    }

    /* not accepted because valueCodableConcept is 1:1
    @Test
    void testPcrDataAbsent() throws IOException {
        testMapping("create-PCR-data-absent.json",
                "paragon-PCR-data-absent.json");
    }*/

    // #####################################################################################
    // check exceptions

    @Test
    void createInvalidStatusCode() throws IOException {
        Exception exception = executeMappingException("create-PCR-status-invalid.json");
        assertEquals("The status UNKNOWN is not supported by the fhir bridge, since it does not accept unfinished entered-in-error or corrected instances. If an fix is necessary, please contact the administrator of the Bridge. Supported is either final, amended, registered or preliminary", exception.getMessage());
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
                .registerValueObject(ProAnalytCluster.class)
                .registerValueObject(StatusDefiningCode.class)
                .registerValueObject(VirusnachweistestDefiningCode.class)
                .build();
    }

    @Override
    public Exception executeMappingException(String path) throws IOException {
        Observation obs = (Observation) testFileLoader.loadResource(path);
        return assertThrows(Exception.class, () ->
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
