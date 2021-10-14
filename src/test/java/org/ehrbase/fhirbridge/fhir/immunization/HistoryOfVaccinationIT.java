package org.ehrbase.fhirbridge.fhir.immunization;

import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.specific.impfstatus.ImpfstatusCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.impfstatuscomposition.ImpfstatusComposition;
import org.ehrbase.fhirbridge.ehr.opt.impfstatuscomposition.definition.ImpfungAction;
import org.ehrbase.fhirbridge.ehr.opt.impfstatuscomposition.definition.ImpfungImpfungGegenElement;
import org.ehrbase.fhirbridge.ehr.opt.impfstatuscomposition.definition.UnbekannterImpfstatusEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.impfstatuscomposition.definition.VerabreichteDosenCluster;
import org.ehrbase.fhirbridge.fhir.AbstractMappingTestSetupIT;
import org.hl7.fhir.r4.model.Immunization;
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

/**
 * Integration tests for {@link Immunization} using History of Vaccination profile.
 */
class HistoryOfVaccinationIT extends AbstractMappingTestSetupIT {
    public HistoryOfVaccinationIT() {
        super("Immunization/", Immunization.class);
    }

    @Test
    void createImmunization() throws IOException {
        create("create-immunization-for-patient-complete.json");
    }

    @Test
    void mappingCreateImmunization1() throws IOException{
        testMapping("create-immunization-for-patient-complete.json",
                "paragon-create-immunization-for-patient-complete.json");
    }

    @Test
    void mappingCreateImmunization2() throws IOException{
        testMapping("create-immunization-for-patient-complete-2.json",
                "paragon-create-immunization-for-patient-complete-2.json");
    }


    @Test
    void mappingCreateImmunization3() throws IOException{
        testMapping("create-immunization-for-patient-complete-3.json",
                "paragon-create-immunization-for-patient-complete-3.json");
    }


    @Test
    void mappingCreateImmunization4() throws IOException{
        testMapping("create-immunization-for-patient-complete-4.json",
                "paragon-create-immunization-for-patient-complete-4.json");
    }

    @Test
    void mappingCreateImmunizationNotDone() throws IOException{
        testMapping("create-immunization-for-patient-not-done.json",
                "paragon-create-immunization-for-patient-not-done.json");
    }

    @Override
    public Exception executeMappingException(String path) throws IOException {
        Immunization immunization = (Immunization) testFileLoader.loadResource(path);
        return assertThrows(Exception.class, () -> {
            new ImpfstatusCompositionConverter().convert(immunization);
        });
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        Immunization immunization = (Immunization) super.testFileLoader.loadResource(resourcePath);
        ImpfstatusCompositionConverter impfstatusCompositionConverter = new ImpfstatusCompositionConverter();
        ImpfstatusComposition mappedImpfstatusComposition = impfstatusCompositionConverter.convert(immunization);
        Diff diff = compareCompositions(getJavers(), paragonPath, mappedImpfstatusComposition);
        assertEquals(0, diff.getChanges().size());
    }


    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(ImpfstatusComposition.class, List.of("location", "feederAudit", "startTimeValue"))) // excluded startTimeValue since its a special case in this mapping
                .registerValueObject(UnbekannterImpfstatusEvaluation.class)
                .registerValueObject(ImpfungAction.class)
                .registerValueObject(VerabreichteDosenCluster.class)
                .registerValueObject(ImpfungImpfungGegenElement.class)
                .build();
    }

}
