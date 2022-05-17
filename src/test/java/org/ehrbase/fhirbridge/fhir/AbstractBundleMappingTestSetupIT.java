package org.ehrbase.fhirbridge.fhir;

import ca.uhn.fhir.parser.DataFormatException;
import ca.uhn.fhir.parser.IParser;
import org.hl7.fhir.r4.model.Bundle;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractBundleMappingTestSetupIT extends AbstractMappingTestSetupIT {


    public AbstractBundleMappingTestSetupIT(String directory, Class clazz) {
        super(directory, clazz);
    }

    @Override
    protected void create(String path) throws IOException {
        String resource = testFileLoader.loadResourceToString(path);
        String outcome = client.transaction().withBundle(resource.replaceAll(PATIENT_ID_TOKEN, PATIENT_ID)).execute();
        String fileEnding = path.substring(path.length() - 4);
        IParser parser;
        if (fileEnding.equals(".xml")) {
            parser = context.newXmlParser();
        } else if (fileEnding.equals("json")) {
            parser = context.newJsonParser();
        } else {
            throw new DataFormatException("File ending has to be either .json or .xml");
        }
        Bundle bundle = parser.parseResource(Bundle.class, outcome);

        assertNotNull(bundle.getId());
    }

}
