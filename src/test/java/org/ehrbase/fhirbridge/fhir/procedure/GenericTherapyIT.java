package org.ehrbase.fhirbridge.fhir.procedure;


import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConversionException;
import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.TherapyCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.GECCOProzedurComposition;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition.*;
import org.ehrbase.fhirbridge.fhir.AbstractMappingTestSetupIT;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.Procedure;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.metamodel.clazz.ValueObjectDefinition;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.temporal.TemporalAccessor;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GenericTherapyIT extends AbstractMappingTestSetupIT {

    public GenericTherapyIT() {
        super("Procedure/Therapy/", Procedure.class);
    }

    @Test
    void createApheresisProcedure() throws IOException {
        create("apheresis-example-1.json");
        testMapping("mapping/apheresis-example-1.json","mapping/apheresis-example-1-result.json");
        testMapping("mapping/apheresis-example-2.json","mapping/apheresis-example-2-result.json");
        testMapping("mapping/apheresis-example-3.json","mapping/apheresis-example-3-result.json");
        testMapping("mapping/apheresis-example-4.json","mapping/apheresis-example-4-result.json");
        testMapping("mapping/apheresis-example-5.json","mapping/apheresis-example-5-result.json");
    }

    @Test
    void createDialysisProcedure() throws IOException {
        testMapping("mapping/dialysis-example-1.json","mapping/dialysis-example-1-result.json");
        testMapping("mapping/dialysis-example-2.json","mapping/dialysis-example-2-result.json");
        testMapping("mapping/dialysis-example-3.json","mapping/dialysis-example-3-result.json");
        testMapping("mapping/dialysis-example-4.json","mapping/dialysis-example-4-result.json");
        testMapping("mapping/dialysis-example-5.json","mapping/dialysis-example-5-result.json");
        testMapping("mapping/dialysis-example-6.json","mapping/dialysis-example-6-result.json");

    }

    @Test
    void createExtracorporealMembraneOxygenation() throws IOException {
        testMapping("mapping/extracorporeal-membrane-oxygenation-example-1.json","mapping/extracorporeal-membrane-oxygenation-example-1-result.json");
        testMapping("mapping/extracorporeal-membrane-oxygenation-example-3.json","mapping/extracorporeal-membrane-oxygenation-example-3-result.json");
        testMapping("mapping/extracorporeal-membrane-oxygenation-example-4.json","mapping/extracorporeal-membrane-oxygenation-example-4-result.json");
        testMapping("mapping/extracorporeal-membrane-oxygenation-example-2.json","mapping/extracorporeal-membrane-oxygenation-example-2-result.json");

    }

    @Test
    void createPronePosition() throws IOException {
        testMapping("mapping/prone-position-example-1.json","mapping/prone-position-example-1-result.json");
        testMapping("mapping/prone-position-example-2.json","mapping/prone-position-example-2-result.json");
        testMapping("mapping/prone-position-example-3.json","mapping/prone-position-example-3-result.json");
        testMapping("mapping/prone-position-example-4.json","mapping/prone-position-example-4-result.json");

    }

    @Test
    void createRadiologyProcedures() throws IOException {
        testMapping("mapping/radiology-example-1.json","mapping/radiology-example-1-result.json");
        testMapping("mapping/radiology-example-2.json","mapping/radiology-example-2-result.json");
        testMapping("mapping/radiology-example-3.json","mapping/radiology-example-3-result.json");
        testMapping("mapping/radiology-example-4.json","mapping/radiology-example-4-result.json");
    }

    @Test
    void createRespiratoryTherapies() throws IOException {
        testMapping("mapping/respiratory-therapies-example-1.json","mapping/respiratory-therapies-example-1-result.json");
        testMapping("mapping/respiratory-therapies-example-2.json","mapping/respiratory-therapies-example-2-result.json");
        testMapping("mapping/respiratory-therapies-example-3.json","mapping/respiratory-therapies-example-3-result.json");
        testMapping("mapping/respiratory-therapies-example-4.json","mapping/respiratory-therapies-example-4-result.json");
        testMapping("mapping/respiratory-therapies-example-5.json","mapping/respiratory-therapies-example-5-result.json");
        testMapping("mapping/respiratory-therapies-example-7.json","mapping/respiratory-therapies-example-7-result.json");
        testMapping("mapping/respiratory-therapies-example-8.json","mapping/respiratory-therapies-example-8-result.json");
        testMapping("mapping/respiratory-therapies-example-11.json","mapping/respiratory-therapies-example-11-result.json");
        testMapping("mapping/respiratory-therapies-example-10.json","mapping/respiratory-therapies-example-10-result.json");
        testMapping("mapping/respiratory-therapies-example-9.json","mapping/respiratory-therapies-example-9-result.json");
        testMapping("mapping/respiratory-therapies-example-6.json","mapping/respiratory-therapies-example-6-result.json");
    }


    void testMapping(String resourcePath, String resultPath) throws IOException {
        Procedure procedure = (Procedure) super.testFileLoader.loadResource(resourcePath);
        TherapyCompositionConverter therapyCompositionConverter = new TherapyCompositionConverter();
        GECCOProzedurComposition mappedProzedurComposition = therapyCompositionConverter.toComposition(procedure);
        Diff diff = compareCompositions(getJavers(), resultPath, mappedProzedurComposition);
        assertEquals(diff.getChanges().size(), 0);
    }


    @Test
    void createApheresisWithInvalidCode() throws IOException {
        Exception exception = executeMappingUnprocessableEntityException(super.testFileLoader.loadResource("invalid/apheresis-invalid-code.json"));
        assertEquals("Some parts of the not present procedure did not contain the required elements. Invalid name of procedure", exception.getMessage());
    }

    @Test
    void createRadiologyWithInvalidBodySite() throws IOException {
        Exception exception = executeMappingUnprocessableEntityException(super.testFileLoader.loadResource("invalid/radiology-example-invalid-body-site.json"));
        assertEquals("Some parts of the present procedure did not contain the required elements. Invalid body site for PLAIN_RADIOGRAPHY", exception.getMessage());
    }

    @Test
    void createRespiratoryTherapiesWithInvalidMedicalDevice() throws IOException {
        Exception exception = executeMappingUnprocessableEntityException(super.testFileLoader.loadResource("invalid/respiratory-therapies-invalid-medical-device.json"));
        assertEquals("Some parts of the present procedure did not contain the required elements. Invalid medical device code", exception.getMessage());
    }

    @Override
    public Exception executeMappingUnprocessableEntityException(IBaseResource procedure) {
        return assertThrows(CompositionConversionException.class, () -> {
            new TherapyCompositionConverter().toComposition((Procedure) procedure);
        });
    }

    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(GECCOProzedurComposition.class, List.of("location")))
                .registerValueObject((ProzedurAction.class))
                .registerValueObject((NichtDurchgefuehrteProzedurEvaluation.class))
                .registerValueObject((UnbekannteProzedurEvaluation.class))
                .registerValueObject((GeccoProzedurKategorieElement.class))
                .registerValueObject((MedizingeraetCluster.class))
                .build();
    }
}

