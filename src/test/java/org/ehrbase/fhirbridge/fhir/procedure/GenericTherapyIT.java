package org.ehrbase.fhirbridge.fhir.procedure;

import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.specific.therapy.TherapyCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.GECCOProzedurComposition;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition.*;
import org.ehrbase.fhirbridge.fhir.AbstractMappingTestSetupIT;
import org.hl7.fhir.r4.model.Procedure;
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

class GenericTherapyIT extends AbstractMappingTestSetupIT {

    public GenericTherapyIT() {
        super("Procedure/Therapy/", Procedure.class);
    }

    @Test
    void testCreate() throws IOException {
        create("apheresis-example-1.json");
    }

    @Test
    void createApheresisProcedure() throws IOException {
        testMapping("mapping/apheresis-example-1.json", "mapping/apheresis-example-1-result.json");
        testMapping("mapping/apheresis-example-2.json", "mapping/apheresis-example-2-result.json");
        testMapping("mapping/apheresis-example-3.json", "mapping/apheresis-example-3-result.json");
    }

    @Test
    void createDialysisProcedure() throws IOException {
        testMapping("mapping/dialysis-example-1.json", "mapping/dialysis-example-1-result.json");
        testMapping("mapping/dialysis-example-2.json", "mapping/dialysis-example-2-result.json");
        testMapping("mapping/dialysis-example-3.json", "mapping/dialysis-example-3-result.json");

    }

    @Test
    void createExtracorporealMembraneOxygenation() throws IOException {
        testMapping("mapping/extracorporeal-membrane-oxygenation-example-1.json", "mapping/extracorporeal-membrane-oxygenation-example-1-result.json");
        testMapping("mapping/extracorporeal-membrane-oxygenation-example-3.json", "mapping/extracorporeal-membrane-oxygenation-example-3-result.json");//TODO i stopped here
        testMapping("mapping/extracorporeal-membrane-oxygenation-example-2.json", "mapping/extracorporeal-membrane-oxygenation-example-2-result.json");

    }

    @Test
    void createPronePosition() throws IOException {
        testMapping("mapping/prone-position-example-1.json", "mapping/prone-position-example-1-result.json");
        testMapping("mapping/prone-position-example-2.json", "mapping/prone-position-example-2-result.json");
        testMapping("mapping/prone-position-example-3.json", "mapping/prone-position-example-3-result.json");

    }

    @Test
    void createRadiologyProcedures() throws IOException {
        testMapping("mapping/radiology-example-1.json", "mapping/radiology-example-1-result.json");
        testMapping("mapping/radiology-example-2.json", "mapping/radiology-example-2-result.json");
        testMapping("mapping/radiology-example-3.json", "mapping/radiology-example-3-result.json");
    }

    @Test
    void createRespiratoryTherapies() throws IOException {
        testMapping("mapping/respiratory-therapies-example-1.json", "mapping/respiratory-therapies-example-1-result.json");
        testMapping("mapping/respiratory-therapies-example-2.json", "mapping/respiratory-therapies-example-2-result.json");
        testMapping("mapping/respiratory-therapies-example-3.json", "mapping/respiratory-therapies-example-3-result.json");
    }

    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        Procedure procedure = (Procedure) super.testFileLoader.loadResource(resourcePath);
        TherapyCompositionConverter therapyCompositionConverter = new TherapyCompositionConverter();
        GECCOProzedurComposition mappedProzedurComposition = therapyCompositionConverter.convert(procedure);
        Diff diff = compareCompositions(getJavers(), paragonPath, mappedProzedurComposition);
        assertEquals(diff.getChanges().size(), 0);
    }

    @Override
    public Exception executeMappingException(String resource) throws IOException {
        Procedure procedure = (Procedure) testFileLoader.loadResource(resource);

        return assertThrows(ConversionException.class, () -> new TherapyCompositionConverter().convert(procedure));
    }

    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(GECCOProzedurComposition.class, List.of("location", "feederAudit")))
                .registerValueObject((ProzedurAction.class))
                .registerValueObject((NichtDurchgefuehrteProzedurEvaluation.class))
                .registerValueObject((UnbekannteProzedurEvaluation.class))
                .registerValueObject((GeccoProzedurKategorieElement.class))
                .registerValueObject((MedizingeraetCluster.class))
                .registerValueObject((ProzedurKoerperstelleElement.class))
                .build();
    }
}

