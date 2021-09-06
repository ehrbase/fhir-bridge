package org.ehrbase.fhirbridge.fhir.documentreference;

import ca.uhn.fhir.rest.api.MethodOutcome;
import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.specific.hipdocument.DocumentReferenceToHipDocumentConverter;
import org.ehrbase.fhirbridge.ehr.opt.hipdocumentcomposition.HIPDocumentComposition;
import org.ehrbase.fhirbridge.ehr.opt.hipdocumentcomposition.definition.HipMetadataCluster;
import org.ehrbase.fhirbridge.ehr.opt.hipdocumentcomposition.definition.HipMetadataIdentifikatorElement;
import org.ehrbase.fhirbridge.ehr.opt.hipdocumentcomposition.definition.MediendateiCluster;
import org.ehrbase.fhirbridge.fhir.AbstractMappingTestSetupIT;
import org.hl7.fhir.r4.model.DocumentReference;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.metamodel.clazz.ValueObjectDefinition;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.temporal.TemporalAccessor;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DocumentReferenceIT extends AbstractMappingTestSetupIT {

    public DocumentReferenceIT() {
        super("DocumentReference/", DocumentReference.class);
    }

    @Test
    void testDocumentReference() throws IOException {
        String resource = testFileLoader.loadResourceToString("document-reference.json");
        MethodOutcome outcome = client.create().resource(resource.replaceAll(PATIENT_ID_TOKEN, PATIENT_ID)).execute();

        assertEquals(true, outcome.getCreated());
        assertNotNull(outcome.getResource());

    }

    @Test
    void testDocumentReferenceMapping() throws IOException {
        testMapping("document-reference-fake.json", "paragon-document-reference.json");
    }

    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(HIPDocumentComposition.class, List.of("location", "feederAudit", "startTimeValue")))
                .registerValueObject(HipMetadataIdentifikatorElement.class)
                .registerValueObject(HipMetadataCluster.class)
                .registerValueObject(MediendateiCluster.class)
                .build();
    }

    @Override
    public Exception executeMappingException(String resource) throws IOException {
        DocumentReference documentReference = (DocumentReference) testFileLoader.loadResource(resource);
        DocumentReferenceToHipDocumentConverter converter = new DocumentReferenceToHipDocumentConverter();
        return assertThrows(ConversionException.class, () -> converter.convert(documentReference));
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        DocumentReference documentReference = (DocumentReference) super.testFileLoader.loadResource(resourcePath);
        DocumentReferenceToHipDocumentConverter converter = new DocumentReferenceToHipDocumentConverter();
        HIPDocumentComposition result = converter.convert(documentReference);
        Diff diff = compareCompositions(getJavers(), paragonPath, result);
        assertEquals(0, diff.getChanges().size());
    }
}
