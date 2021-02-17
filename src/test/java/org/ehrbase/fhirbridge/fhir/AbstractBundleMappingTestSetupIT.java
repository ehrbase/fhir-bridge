package org.ehrbase.fhirbridge.fhir;

import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.apache.commons.io.IOUtils;
import org.ehrbase.fhirbridge.ehr.converter.bloodgas.BloodGasPanelCompositionConverter;
import org.ehrbase.fhirbridge.fhir.AbstractMappingTestSetupIT;
import org.ehrbase.fhirbridge.fhir.bundle.converter.BloodGasPanelConverter;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.Bundle;
import org.springframework.core.io.ClassPathResource;


import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractBundleMappingTestSetupIT extends AbstractMappingTestSetupIT {


    public AbstractBundleMappingTestSetupIT(String directory, Class clazz) {
        super(directory, clazz);
    }

    @Override
    protected void create(String path) throws IOException {
        String resource = testFileLoader.loadResourceToString(path);
        String outcome = client.transaction().withBundle(resource.replaceAll(PATIENT_ID_TOKEN, PATIENT_ID)).execute();
        IParser parser = context.newJsonParser();
        Bundle bundle = parser.parseResource(Bundle.class, outcome);

        assertNotNull(bundle.getId());
    }

/*
    public abstract Exception executeIntegrationUnprocessabelEntityException(IBaseResource bloodgasPanel);
*/
}
