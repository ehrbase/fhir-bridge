/*
 * Copyright 2020-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.fhirbridge.ehr.controller;

import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.ehr.EhrStatus;
import com.nedap.archie.rm.generic.PartySelf;
import com.nedap.archie.rm.support.identification.GenericId;
import com.nedap.archie.rm.support.identification.PartyRef;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.ehrbase.client.openehrclient.OpenEhrClientConfig;
import org.ehrbase.client.openehrclient.defaultrestclient.DefaultRestClient;
import org.ehrbase.fhirbridge.config.SmartOnFhirConfig;
import org.ehrbase.fhirbridge.ehr.ResourceTemplateProvider;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@EnableConfigurationProperties({SmartOnFhirConfig.class})
@RestController
@RequestMapping("/ehradmin")
public class EhrController {

    private final SmartOnFhirConfig _smartOnFhirConfig  ;

    public EhrController(SmartOnFhirConfig pConfig) {
        _smartOnFhirConfig = pConfig;
    }

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
                new OpenEhrClientConfig(new URI(_smartOnFhirConfig.getEhrBaseUrl())),
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
