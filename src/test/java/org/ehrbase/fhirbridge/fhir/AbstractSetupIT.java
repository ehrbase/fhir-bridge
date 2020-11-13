package org.ehrbase.fhirbridge.fhir;

import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.ehr.EhrStatus;
import com.nedap.archie.rm.generic.PartySelf;
import com.nedap.archie.rm.support.identification.HierObjectId;
import com.nedap.archie.rm.support.identification.PartyRef;
import org.ehrbase.client.openehrclient.OpenEhrClientConfig;
import org.ehrbase.client.openehrclient.defaultrestclient.DefaultRestClient;
import org.ehrbase.fhirbridge.ehr.ClasspathTemplateProvider;
import org.junit.jupiter.api.BeforeAll;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

abstract class AbstractSetupIT {

    static final String PATIENT_ID_TOKEN = "\\{\\{patientId\\}\\}";

    static final String PATIENT_ID = UUID.randomUUID().toString();

    @BeforeAll
    static void setup() throws URISyntaxException {
        DefaultRestClient client = new DefaultRestClient(
                new OpenEhrClientConfig(new URI("http://localhost:8080/ehrbase/rest/openehr/v1/")),
                new ClasspathTemplateProvider());

        EhrStatus ehrStatus = new EhrStatus();
        ehrStatus.setSubject(new PartySelf(new PartyRef(new HierObjectId(PATIENT_ID), "demographic", "PERSON")));
        ehrStatus.setArchetypeNodeId("openEHR-EHR-EHR_STATUS.generic.v1");
        ehrStatus.setName(new DvText("Integration tests status"));
        client.ehrEndpoint().createEhr(ehrStatus);
    }
}
