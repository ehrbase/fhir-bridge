package org.ehrbase.fhirbridge.fhir.observation;

import org.ehrbase.fhirbridge.comparators.CustomTemporalAcessorComparator;
import org.ehrbase.fhirbridge.ehr.converter.specific.mibikultur.MibiBefundConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.mibikultur.MibiKulturNachweisConverter;
import org.ehrbase.fhirbridge.ehr.opt.mikrobiologischerbefundcomposition.MikrobiologischerBefundComposition;
import org.ehrbase.fhirbridge.ehr.opt.mikrobiologischerbefundcomposition.definition.AnatomischeLokalisationCluster;
import org.ehrbase.fhirbridge.ehr.opt.mikrobiologischerbefundcomposition.definition.AntibiogrammCluster;
import org.ehrbase.fhirbridge.ehr.opt.mikrobiologischerbefundcomposition.definition.BefundObservation;
import org.ehrbase.fhirbridge.ehr.opt.mikrobiologischerbefundcomposition.definition.EinsenderstandortCluster;
import org.ehrbase.fhirbridge.ehr.opt.mikrobiologischerbefundcomposition.definition.EmpfaengerstandortCluster;
import org.ehrbase.fhirbridge.ehr.opt.mikrobiologischerbefundcomposition.definition.ErregerdetailsCluster;
import org.ehrbase.fhirbridge.ehr.opt.mikrobiologischerbefundcomposition.definition.FallidentifikationCluster;
import org.ehrbase.fhirbridge.ehr.opt.mikrobiologischerbefundcomposition.definition.KulturCluster;
import org.ehrbase.fhirbridge.ehr.opt.mikrobiologischerbefundcomposition.definition.ProAntibiotikumCluster;
import org.ehrbase.fhirbridge.ehr.opt.mikrobiologischerbefundcomposition.definition.ProErregerCluster;
import org.ehrbase.fhirbridge.ehr.opt.mikrobiologischerbefundcomposition.definition.ProErregerZugehoerigeLaborprobeDvUri;
import org.ehrbase.fhirbridge.ehr.opt.mikrobiologischerbefundcomposition.definition.ProbeCluster;
import org.ehrbase.fhirbridge.ehr.opt.mikrobiologischerbefundcomposition.definition.ProbeZeitpunktDerProbenentnahmeDvDateTime;
import org.ehrbase.fhirbridge.fhir.AbstractMappingTestSetupIT;
import org.ehrbase.fhirbridge.fhir.observation.validator.MibiKulturValidator;
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

public class MikrobiologischerBefundIT  extends AbstractMappingTestSetupIT {

    public MikrobiologischerBefundIT() {
        super("Observation/MikrobiologischerBefund/", Observation.class); //fhir-Resource
    }

    @Test
    void createMibi() throws IOException {
        create("create-mibi.json");
    }

    @Test
    void createMIbiMapping() throws IOException {
        testMapping("create-mibi.json", "paragon-create-mibi.json");
    }

    @Test
    void createMREVREMapping() throws IOException {
        testMapping("create-mibi-mre-MVRE.json", "paragon-create-mibi-mre-MVRE.json");
    }

    @Test
    void createVREMapping() throws IOException {
        testMapping("create-mibi-mre-VRE.json", "paragon-create-mibi-mre-VRE.json");
    }

    @Test
    void createMrgnMapping() throws IOException {
        testMapping("create-mibi-mrgn.json", "paragon-create-mibi-mrgn.json");
    }
    @Test
    void createOhneHemmkonzentrationMapping() throws IOException {
        testMapping("create-mibi-ohneminimal-hemm.json", "paragon-create-mibi-ohneminimal-hemm.json");
    }

    @Test
    void createProbeMapping() throws IOException {
        testMapping("create-mibi-probe.json", "paragon-create-mibi-probe.json");
    }
    @Override
    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValue(TemporalAccessor.class, new CustomTemporalAcessorComparator())
                .registerValueObject(new ValueObjectDefinition(MikrobiologischerBefundComposition.class, List.of("location", "feederAudit")))
                .registerValueObject(FallidentifikationCluster.class)
                .registerValueObject(BefundObservation.class)
                .registerValueObject(EmpfaengerstandortCluster.class)
                .registerValueObject(EinsenderstandortCluster.class)
                .registerValueObject(ProbeCluster.class)
                .registerValueObject(KulturCluster.class)
                .registerValueObject(AnatomischeLokalisationCluster.class)
                .registerValueObject(ProbeZeitpunktDerProbenentnahmeDvDateTime.class)
                .registerValueObject(ProErregerCluster.class)
                .registerValueObject(ErregerdetailsCluster.class)
                .registerValueObject(ProErregerZugehoerigeLaborprobeDvUri.class)
                .registerValueObject(AntibiogrammCluster.class)
                .registerValueObject(ProAntibiotikumCluster.class)
                .build();
    }

    @Override
    public Exception executeMappingException(String path) throws IOException {
        Observation observation = (Observation) testFileLoader.loadResource(path);
        return assertThrows(Exception.class, () ->
                new MibiBefundConverter().convert((observation))
        );
    }


    public Exception executeValidatorException(String path) throws IOException {
        Observation observation = (Observation) testFileLoader.loadResource(path);
        return assertThrows(Exception.class, () -> {
            new MibiKulturValidator().validateRequest(observation, null);
        });
    }

    @Override
    public void testMapping(String resourcePath, String paragonPath) throws IOException {
        Observation observation = (Observation)  super.testFileLoader.loadResource(resourcePath);
        MibiKulturNachweisConverter mibiKulturNachweisConverter = new MibiKulturNachweisConverter();
        MikrobiologischerBefundComposition mapped = mibiKulturNachweisConverter.convert(observation);
        Diff diff = compareCompositions(getJavers(), paragonPath, mapped);
        assertEquals(0, diff.getChanges().size());
    }
}
