package org.ehrbase.fhirbridge.fhir.immunization;

import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.specific.impfstatus.ImpfstatusCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.radiologischerBefund.RadiologischerBefundCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.GECCORadiologischerBefundComposition;
import org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.definition.BildgebendesUntersuchungsergebnisObservation;
import org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.definition.RadiologischerBefundKategorieElement;
import org.ehrbase.fhirbridge.ehr.opt.impfstatuscomposition.ImpfstatusComposition;
import org.ehrbase.fhirbridge.fhir.AbstractMappingTestSetupIT;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.Immunization;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.metamodel.clazz.ValueObjectDefinition;

import java.io.IOException;
import java.time.temporal.TemporalAccessor;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Integration tests for {@link Immunization} using History of Vaccination profile.
 */
public class HistoryOfVaccinationIT extends AbstractMappingTestSetupIT {
    public HistoryOfVaccinationIT(String directory, Class clazz) {
        super("Immunization/", Immunization.class);
    }


    @Override
    public Exception executeMappingException(String path) throws IOException {
        Immunization immunization = (Immunization) testFileLoader.loadResource(path);
        return assertThrows(ConversionException.class, () -> {
            new ImpfstatusCompositionConverter().convert(immunization);
        });
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        Immunization immunization = (Immunization) super.testFileLoader.loadResource(resourcePath);
        ImpfstatusCompositionConverter impfstatusCompositionConverter = new ImpfstatusCompositionConverter();
        ImpfstatusComposition mappedImpfstatusComposition = impfstatusCompositionConverter.convert(immunization);
        Diff diff = compareCompositions(getJavers(), paragonPath, mappedImpfstatusComposition);
        assertEquals(diff.getChanges().size(), 0);
    }

    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(ImpfstatusComposition.class, List.of("location", "feederAudit")))
                .build();
    }

}
