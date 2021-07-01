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

import ca.uhn.fhir.jpa.api.dao.IFhirResourceDao;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.api.RestOperationTypeEnum;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.ehr.EhrStatus;
import com.nedap.archie.rm.generic.PartySelf;
import com.nedap.archie.rm.support.identification.GenericId;
import com.nedap.archie.rm.support.identification.PartyRef;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.fhirbridge.camel.CamelConstants;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConstants;
import org.ehrbase.fhirbridge.core.domain.PatientEhr;
import org.ehrbase.fhirbridge.core.domain.ResourceComposition;
import org.ehrbase.fhirbridge.core.repository.PatientEhrRepository;
import org.ehrbase.fhirbridge.core.repository.ResourceCompositionRepository;
import org.hl7.fhir.r4.model.Patient;
import org.openehealth.ipf.commons.ihe.fhir.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@SuppressWarnings("java:S6212")
public class ProvidePatientProcessor implements Processor {

    private static final Logger LOG = LoggerFactory.getLogger(ProvidePatientProcessor.class);

    private static final String ARCHETYPE_NODE_ID = "openEHR-EHR-EHR_STATUS.generic.v1";

    private final IFhirResourceDao<Patient> patientDao;

    private final PatientEhrRepository patientEhrRepository;

    private final ResourceCompositionRepository resourceCompositionRepository;

    private final OpenEhrClient openEhrClient;

    private final String scheme;

    public ProvidePatientProcessor(IFhirResourceDao<Patient> patientDao,
                                   PatientEhrRepository patientEhrRepository,
                                   ResourceCompositionRepository resourceCompositionRepository,
                                   OpenEhrClient openEhrClient) {
        this.patientDao = patientDao;
        this.patientEhrRepository = patientEhrRepository;
        this.resourceCompositionRepository = resourceCompositionRepository;
        this.openEhrClient = openEhrClient;
        this.scheme = "fhirbridge.ehrbase.org"; // FIXME: Create configuration properties
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        Patient patient = exchange.getIn().getBody(Patient.class);
        RequestDetails requestDetails = exchange.getIn().getHeader(Constants.FHIR_REQUEST_DETAILS, RequestDetails.class);

        switch (requestDetails.getRestOperationType()) {
            case CREATE:
                handleCreate(patient, requestDetails, exchange);
                break;
            case UPDATE:
                handleUpdate(patient, requestDetails, exchange);
                break;
            default:
                throw new IllegalArgumentException("Unsupported operation: " + requestDetails.getRestOperationType());
        }
    }

    private void handleCreate(Patient patient, RequestDetails requestDetails, Exchange exchange) {
        String conditionalUrl = requestDetails.getConditionalUrl(RestOperationTypeEnum.CREATE);
        MethodOutcome outcome = patientDao.create(patient, conditionalUrl, requestDetails);

        UUID ehrId = createEhr(outcome.getId().getIdPart());

        exchange.getMessage().setHeader(CompositionConstants.EHR_ID, ehrId);
        exchange.setProperty(CamelConstants.OUTCOME, outcome);
    }

    private void handleUpdate(Patient patient, RequestDetails requestDetails, Exchange exchange) {
        String conditionalUrl = requestDetails.getConditionalUrl(RestOperationTypeEnum.UPDATE);
        MethodOutcome outcome = patientDao.update(patient, conditionalUrl, requestDetails);

        String patientId = outcome.getId().getIdPart();
        UUID ehrId = patientEhrRepository.findById(patientId)
                .map(PatientEhr::getEhrId)
                .orElseGet(() -> createEhr(patientId));

        exchange.setProperty(CamelConstants.OUTCOME, outcome);
        exchange.getMessage().setHeader(CompositionConstants.EHR_ID, ehrId);

        resourceCompositionRepository.findById(patientId)
                .map(ResourceComposition::getCompositionId)
                .ifPresent(compositionId -> exchange.getMessage().setHeader(CamelConstants.COMPOSITION_ID, compositionId));
    }

    private UUID createEhr(String patientId) {
        PartySelf subject = new PartySelf(new PartyRef(new GenericId(patientId, scheme), "DEMOGRAPHIC", "PERSON"));
        EhrStatus ehrStatus = new EhrStatus(ARCHETYPE_NODE_ID, new DvText("EHR Status"), subject, true, true, null);
        UUID ehrId = openEhrClient.ehrEndpoint().createEhr(ehrStatus);

        PatientEhr patientEhr = new PatientEhr(patientId, ehrId);
        patientEhrRepository.save(patientEhr);
        LOG.debug("Created PatientEhr: {}", patientEhr);

        return ehrId;
    }
}
