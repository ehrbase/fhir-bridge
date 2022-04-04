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
import org.ehrbase.fhirbridge.fhir.support.PatientUtils;
import org.hl7.fhir.instance.model.api.IIdType;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.ResourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * {@link org.apache.camel.Processor Processor} that retrieves the EHR ID of the patient involved in the current
 * exchange. If the patient does not have an EHR, a new one is created and the EHR ID is stored in the database.
 *
 * @since 1.2.0
 */
@Component(EhrLookupProcessor.BEAN_ID)
@SuppressWarnings("java:S6212")
public class EhrLookupProcessor implements FhirRequestProcessor {

    public static final String BEAN_ID = "ehrLookupProcessor";

    private static final Logger LOG = LoggerFactory.getLogger(EhrLookupProcessor.class);

    private static final String ARCHETYPE_NODE_ID = "openEHR-EHR-EHR_STATUS.generic.v1";

    private final IFhirResourceDao<Patient> patientDao;

    private final PatientEhrRepository patientEhrRepository;

    private final OpenEhrClient openEhrClient;

    public EhrLookupProcessor(IFhirResourceDao<Patient> patientDao, PatientEhrRepository patientEhrRepository,
                              OpenEhrClient openEhrClient) {
        this.patientDao = patientDao;
        this.patientEhrRepository = patientEhrRepository;
        this.openEhrClient = openEhrClient;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        IIdType patientId = getPatientId(exchange);

        UUID ehrId = patientEhrRepository.findById(patientId.getIdPart())
                .map(PatientEhr::getEhrId)
                .orElseGet(() -> createEhr(patientId));

        exchange.getMessage().setHeader(CompositionConstants.EHR_ID, ehrId);
    }

    /**
     * Gets the current patient ID.
     *
     * @param exchange the current exchange
     * @return the patient ID
     */
    private IIdType getPatientId(Exchange exchange) {
        Resource resource = exchange.getIn().getBody(Resource.class);

        if (resource.getResourceType() == ResourceType.Patient) {
            return exchange.getProperty(CamelConstants.OUTCOME, MethodOutcome.class).getId();
        } else {
            return exchange.getIn().getHeader(CamelConstants.PATIENT_ID, IIdType.class);
        }
    }

    /**
     * Creates an EHR for the given patient ID.
     *
     * @param patientId the given patient ID
     * @return the EHR ID
     */
    private UUID createEhr(IIdType patientId) {
        Patient patient = patientDao.read(patientId);
        Identifier pseudonym = PatientUtils.getPseudonym(patient);

        PartySelf subject = new PartySelf(new PartyRef(new HierObjectId(pseudonym.getValue()), "DEMOGRAPHIC", "PERSON"));
        EhrStatus ehrStatus = new EhrStatus(ARCHETYPE_NODE_ID, new DvText("EHR Status"), subject, true, true, null);
        UUID ehrId = openEhrClient.ehrEndpoint().createEhr(ehrStatus);

        PatientEhr patientEhr = new PatientEhr(patientId.getIdPart(), ehrId);
        patientEhrRepository.save(patientEhr);
        LOG.debug("Created PatientEhr: patientId={}, ehrId={}", patientEhr.getPatientId(), patientEhr.getEhrId());

        return ehrId;
    }
}
