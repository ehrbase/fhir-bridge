package org.ehrbase.fhirbridge.fhir;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;
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

    protected static final String PATIENT_ID_TOKEN = "\\{\\{patientId\\}\\}";

    protected static String PATIENT_ID;

    protected final FhirContext context;

    protected final IGenericClient client;

    protected TestFileLoader testFileLoader;

    public AbstractSetupIT(String directory, Class clazz) {
        context = FhirContext.forR4();
        context.getRestfulClientFactory().setSocketTimeout(60 * 1000);
        client = context.newRestfulGenericClient("http://localhost:8888/fhir-bridge/fhir/");
        this.testFileLoader = new TestFileLoader(directory,clazz, context);
    }

    public AbstractSetupIT() {
        context = FhirContext.forR4();
        context.getRestfulClientFactory().setSocketTimeout(60 * 1000);
        client = context.newRestfulGenericClient("http://localhost:8888/fhir-bridge/fhir/");
    }

    @BeforeAll
    static void setup() throws URISyntaxException {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("myuser", "myPassword432"));

        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultCredentialsProvider(credentialsProvider)
                .build();

        DefaultRestClient client = new DefaultRestClient(
                new OpenEhrClientConfig(new URI("http://localhost:8080/ehrbase/rest/openehr/v1/")),
                new ResourceTemplateProvider("classpath:/opt/*.opt"),
                httpClient);

        PATIENT_ID = UUID.randomUUID().toString();

        EhrStatus ehrStatus = new EhrStatus();
        ehrStatus.setSubject(new PartySelf(new PartyRef(new HierObjectId(PATIENT_ID), "demographic", "PERSON")));
        ehrStatus.setArchetypeNodeId("openEHR-EHR-EHR_STATUS.generic.v1");
        ehrStatus.setName(new DvText("Integration tests status"));
        client.ehrEndpoint().createEhr(ehrStatus);
    }

    public Diff compareCompositions(Javers javers, String paragonFilePath, Composition mappedComposition)
            throws IOException {
        RMObject composition = new CanonicalJson().unmarshal(IOUtils.toString(new ClassPathResource(paragonFilePath).getInputStream(), StandardCharsets.UTF_8), com.nedap.archie.rm.composition.Composition.class);
        ResourceTemplateProvider resourceTemplateProvider = new ResourceTemplateProvider("classpath:/opt/");
        resourceTemplateProvider.afterPropertiesSet();

        Flattener cut = new Flattener(resourceTemplateProvider);
        Composition paragonComposition = cut.flatten(composition, mappedComposition.getClass());
        Diff diff = javers.compare(paragonComposition, mappedComposition);
        diff.getChanges().forEach(System.out::println);
        return diff;
    }

    protected void create(String path) throws IOException {
        String resource = testFileLoader.loadResourceToString(path);
        MethodOutcome outcome = client.create().resource(resource.replaceAll(PATIENT_ID_TOKEN, PATIENT_ID)).execute();

        assertNotNull(outcome.getId());
        assertEquals(true, outcome.getCreated());
    }

    public abstract Javers getJavers();
    public abstract Exception executeMappingUnprocessableEntityException(IBaseResource questionnaireResponse);



}
