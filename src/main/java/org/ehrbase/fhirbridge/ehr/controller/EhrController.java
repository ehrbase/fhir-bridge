package org.ehrbase.fhirbridge.ehr.controller;

import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.ehr.EhrStatus;
import com.nedap.archie.rm.generic.PartySelf;
import com.nedap.archie.rm.support.identification.GenericId;
import com.nedap.archie.rm.support.identification.HierObjectId;
import com.nedap.archie.rm.support.identification.PartyRef;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.ehrbase.client.openehrclient.OpenEhrClientConfig;
import org.ehrbase.client.openehrclient.defaultrestclient.DefaultRestClient;
import org.ehrbase.fhirbridge.ehr.ResourceTemplateProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/ehradmin")
public class EhrController {

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> createEHR(@RequestParam String pPatientId) throws URISyntaxException {
        try {
            CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("myuser", "myPassword432"));

            CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultCredentialsProvider(credentialsProvider)
                .build();

            DefaultRestClient client = new DefaultRestClient(
                new OpenEhrClientConfig(new URI("http://localhost:8091/ehrbase/rest/openehr/v1/")),
                new ResourceTemplateProvider("classpath:/opt/*.opt"),
                httpClient);

            //taken from EhrBase tests
            PartySelf subject = new PartySelf();
            PartyRef externalRef = new PartyRef();
            externalRef.setType("PARTY_REF");
            externalRef.setNamespace("patients");
            GenericId genericId = new GenericId();
            genericId.setScheme("id_scheme");
            genericId.setValue(pPatientId);
            externalRef.setId(genericId);
            subject.setExternalRef(externalRef);
            DvText dvText = new DvText("any EHR status");
            EhrStatus ehrStatus = new EhrStatus("openEHR-EHR-ITEM_TREE.generic.v1", dvText, subject, true, true, null);

            return new ResponseEntity<>(
                client.ehrEndpoint().createEhr(ehrStatus).toString(),
                HttpStatus.OK
            );
        } catch (Exception ex) {
            return new ResponseEntity<>(
                "Error while creating an EHR for patient id " + pPatientId,
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
