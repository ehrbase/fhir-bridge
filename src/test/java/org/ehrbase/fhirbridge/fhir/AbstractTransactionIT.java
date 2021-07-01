package org.ehrbase.fhirbridge.fhir;

import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.gclient.IUpdateTyped;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.Bundle;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public class AbstractTransactionIT extends AbstractSetupIT {

    protected MethodOutcome create(String resourceLocation) throws IOException {
        return client.create()
                .resource(getResourceAsString(resourceLocation))
                .execute();
    }

    protected MethodOutcome update(String resourceLocation) throws IOException {
        return update(resourceLocation, null);
    }

    protected MethodOutcome update(String resourceLocation, String conditionalUrl) throws IOException {
        IUpdateTyped update = client.update()
                .resource(getResourceAsString(resourceLocation));

        if (conditionalUrl != null) {
            update.conditionalByUrl(conditionalUrl);
        }

        return update.execute();
    }

    protected <T extends IBaseResource> T read(String id, Class<T> resourceType) {
        return client.read()
                .resource(resourceType)
                .withId(id)
                .execute();
    }

    protected <T extends IBaseResource> T vread(String id, String versionId, Class<T> resourceType) {
        return client.read()
                .resource(resourceType)
                .withIdAndVersion(id, versionId)
                .execute();
    }

    protected Bundle search(String searchUrl) {
        return (Bundle) client.search()
                .byUrl(searchUrl)
                .execute();
    }

    protected String getResourceAsString(String resourceLocation) throws IOException {
        Reader reader = new InputStreamReader(new ClassPathResource(resourceLocation).getInputStream(), StandardCharsets.UTF_8);
        String resource = FileCopyUtils.copyToString(reader);
        return resource.replaceAll(PATIENT_ID_TOKEN, PATIENT_ID);
    }
}
