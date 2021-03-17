package org.ehrbase.fhirbridge.fhir;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.client.api.ServerValidationModeEnum;
import ca.uhn.fhir.rest.client.interceptor.BearerTokenAuthInterceptor;
import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.ehr.EhrStatus;
import com.nedap.archie.rm.generic.PartySelf;
import com.nedap.archie.rm.support.identification.HierObjectId;
import com.nedap.archie.rm.support.identification.PartyRef;
import org.apache.commons.io.IOUtils;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.ehrbase.client.flattener.Flattener;
import org.ehrbase.client.openehrclient.OpenEhrClientConfig;
import org.ehrbase.client.openehrclient.defaultrestclient.DefaultRestClient;
import org.ehrbase.fhirbridge.ehr.Composition;
import org.ehrbase.fhirbridge.ehr.ResourceTemplateProvider;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.javers.core.Javers;
import org.javers.core.diff.Diff;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractSetupIT {

    public static final String PATIENT_ID_TOKEN = "\\{\\{patientId\\}\\}";

    public static String PATIENT_ID;

    protected final FhirContext context;

    protected final IGenericClient client;
    private static final String BEARER_TOKEN = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJWN1pCbU9MTFFSWmtDVHFxS2J4djFMNHFTVzFyS280WnJ1eEpKUkFQUjNNIn0.eyJleHAiOjE2MTU5MjEwNzAsImlhdCI6MTYxNTkxNzQ3MCwianRpIjoiNjJkNmY3YmYtMWY4NC00YTZiLWI1MTktMjkwYTUzZmQxNzUxIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDg4L2F1dGgvcmVhbG1zL21hc3RlciIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiJjMmZlYjU5OS0yMmJmLTQzODgtOGJjOS0xNjYxZWVkN2YyNmEiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJTT0YtdGVzdCIsInNlc3Npb25fc3RhdGUiOiI2MGI5NjcwMS05OWE4LTQ1OWYtYWM5Mi1hYjNhYzBhOGZlN2QiLCJhY3IiOiIxIiwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6Im9wZW5pZCBlbWFpbCBwcm9maWxlIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJwYXRpZW50IjoiNTciLCJuYW1lIjoic2VyZWYgc2VyZWYiLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJzZXJlZiIsImdpdmVuX25hbWUiOiJzZXJlZiIsImZhbWlseV9uYW1lIjoic2VyZWYiLCJlbWFpbCI6InNlcmVmMUB4aW5vLmlvIn0.fYG8FCic24MmG291bh8hLg0FZjLIqUt9kpUXV8-BUs_28YSNFay8CjQkHALw-8L1-uM891NVnDqd6wYtU-1_vGf9Ifm36jhjTvZv7XNNrKmx27aWn3PJ3KpGqoweSJivzPiWul80tOiRrx6gGS-ktg0RZkhdGDplEon91a5W_mo43OJfBGigTQ3_V5puHfW16z0qx-mSmOFRfLLBZwmeK4Frmj310s7W5pQZ3GOtj_u0BUF3jnT9_12vdHjsJi_xlIMdL-Re07RCPqHNBPiIYq-OCMtwc6qzazCGunCee1b-PP8fNe4sdLX1hUPzZ6ys7oek5DkOMKAs5u8mEe98eA";

    public AbstractSetupIT() {
        context = FhirContext.forR4();
        context.getRestfulClientFactory().setSocketTimeout(60 * 1000);
        client = context.newRestfulGenericClient("http://localhost:8888/fhir-bridge/fhir/");

        BearerTokenAuthInterceptor tokenInterceptor = new BearerTokenAuthInterceptor(BEARER_TOKEN);
        client.registerInterceptor(tokenInterceptor);
    }

    @BeforeAll
    static void setup() throws URISyntaxException {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("myuser", "myPassword432"));

        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultCredentialsProvider(credentialsProvider)
                .build();

        DefaultRestClient client = new DefaultRestClient(
                new OpenEhrClientConfig(new URI("http://localhost:8091/ehrbase/rest/openehr/v1/")),
                new ResourceTemplateProvider("classpath:/opt/*.opt"),
                httpClient);

        PATIENT_ID = UUID.randomUUID().toString();

        EhrStatus ehrStatus = new EhrStatus();
        ehrStatus.setSubject(new PartySelf(new PartyRef(new HierObjectId(PATIENT_ID), "demographic", "PERSON")));
        ehrStatus.setArchetypeNodeId("openEHR-EHR-EHR_STATUS.generic.v1");
        ehrStatus.setName(new DvText("Integration tests status"));
        client.ehrEndpoint().createEhr(ehrStatus);
    }




}
