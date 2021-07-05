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

package org.ehrbase.fhirbridge.camel.processor;

import ca.uhn.fhir.rest.api.MethodOutcome;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.ehr.EhrStatus;
import com.nedap.archie.rm.generic.PartySelf;
import com.nedap.archie.rm.support.identification.HierObjectId;
import com.nedap.archie.rm.support.identification.PartyRef;
import org.apache.camel.Exchange;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.fhirbridge.camel.CamelConstants;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConstants;
import org.ehrbase.fhirbridge.core.domain.PatientEhr;
import org.ehrbase.fhirbridge.core.repository.PatientEhrRepository;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.ResourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@SuppressWarnings("java:S6212")
public class EhrLookupProcessor implements FhirRequestProcessor {

    private static final Logger LOG = LoggerFactory.getLogger(EhrLookupProcessor.class);

    private static final String ARCHETYPE_NODE_ID = "openEHR-EHR-EHR_STATUS.generic.v1";

    private final PatientEhrRepository patientEhrRepository;

    private final OpenEhrClient openEhrClient;

    public EhrLookupProcessor(PatientEhrRepository patientEhrRepository, OpenEhrClient openEhrClient) {
        this.patientEhrRepository = patientEhrRepository;
        this.openEhrClient = openEhrClient;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        String patientId = getPatientId(exchange);

        UUID ehrId = patientEhrRepository.findById(patientId)
                .map(PatientEhr::getEhrId)
                .orElseGet(() -> createEhr(patientId));

        exchange.getMessage().setHeader(CompositionConstants.EHR_ID, ehrId);
    }

    private String getPatientId(Exchange exchange) {
        Resource resource = exchange.getIn().getBody(Resource.class);

        if (resource.getResourceType() == ResourceType.Patient) {
            return exchange.getProperty(CamelConstants.OUTCOME, MethodOutcome.class).getId().getIdPart();
        } else {
            return exchange.getIn().getHeader(CamelConstants.PATIENT_ID, String.class);
        }
    }

    private UUID createEhr(String patientId) {
        PartySelf subject = new PartySelf(new PartyRef(new HierObjectId(patientId), "DEMOGRAPHIC", "PERSON"));
        EhrStatus ehrStatus = new EhrStatus(ARCHETYPE_NODE_ID, new DvText("EHR Status"), subject, true, true, null);
        UUID ehrId = openEhrClient.ehrEndpoint().createEhr(ehrStatus);

        PatientEhr patientEhr = new PatientEhr(patientId, ehrId);
        patientEhrRepository.save(patientEhr);
        LOG.debug("Created PatientEhr: patientId={}, ehrId={}", patientEhr.getPatientId(), patientEhr.getEhrId());

        return ehrId;
    }
}
