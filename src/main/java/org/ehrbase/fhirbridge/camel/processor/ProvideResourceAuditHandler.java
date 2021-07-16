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
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.ehrbase.fhirbridge.camel.CamelConstants;
import org.hl7.fhir.r4.model.AuditEvent;
import org.hl7.fhir.r4.model.BooleanType;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.InstantType;
import org.hl7.fhir.r4.model.Reference;
import org.openehealth.ipf.commons.audit.AuditContext;
import org.openehealth.ipf.commons.ihe.fhir.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

/**
 * {@link Processor} that implements audit for <code>create</code> and <code>update</code> operations.
 * <p>
 * This processor is not using ATNA profile but registers an {@link AuditEvent} in the local database.
 *
 * @since 1.0.0
 */
@Component(ProvideResourceAuditHandler.BEAN_ID)
@SuppressWarnings("java:S6212")
public class ProvideResourceAuditHandler implements FhirRequestProcessor {

    public static final String BEAN_ID = "provideResourceAuditHandler";

    private static final Logger LOG = LoggerFactory.getLogger(ProvideResourceAuditHandler.class);

    private final AuditContext auditContext;

    private final IFhirResourceDao<AuditEvent> auditEventDao;

    public ProvideResourceAuditHandler(AuditContext auditContext, IFhirResourceDao<AuditEvent> auditEventDao) {
        this.auditContext = auditContext;
        this.auditEventDao = auditEventDao;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        RequestDetails requestDetails = getRequestDetails(exchange);

        AuditEvent auditEvent =
                new AuditEvent(new Coding("http://terminology.hl7.org/CodeSystem/audit-event-type", "rest", "RESTful Operation"),
                        new InstantType(new Date()), getSource());

        getSubtype(requestDetails).ifPresent(auditEvent::addSubtype);
        getAction(requestDetails).ifPresent(auditEvent::setAction);

        Throwable ex = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Throwable.class);
        if (ex == null) {
            auditEvent.setOutcome(AuditEvent.AuditEventOutcome._0);
            auditEvent.setOutcomeDesc("Operation completed successfully");
        } else {
            auditEvent.setOutcome(AuditEvent.AuditEventOutcome._8);
            auditEvent.setOutcomeDesc("Operation failed: " + ex.getMessage());
        }

        auditEvent.addAgent(getAgent(exchange));
        getEntity(exchange, requestDetails).ifPresent(auditEvent::addEntity);

        MethodOutcome outcome = auditEventDao.create(auditEvent, requestDetails);
        LOG.debug("Created AuditEvent: id={}", outcome.getId());
    }

    private AuditEvent.AuditEventSourceComponent getSource() {
        return new AuditEvent.AuditEventSourceComponent()
                .setObserver(new Reference().setDisplay(auditContext.getAuditSourceId()))
                .addType(new Coding("http://terminology.hl7.org/CodeSystem/security-source-type", "4", "Application Server"));
    }

    private Optional<AuditEvent.AuditEventAction> getAction(RequestDetails requestDetails) {
        switch (requestDetails.getRestOperationType()) {
            case CREATE:
                return Optional.of(AuditEvent.AuditEventAction.C);
            case READ:
            case VREAD:
                return Optional.of(AuditEvent.AuditEventAction.R);
            case UPDATE:
                return Optional.of(AuditEvent.AuditEventAction.U);
            case SEARCH_SYSTEM:
                return Optional.of(AuditEvent.AuditEventAction.E);
            default:
                return Optional.empty();
        }
    }

    private Optional<Coding> getSubtype(RequestDetails requestDetails) {
        RestOperationTypeEnum operation = requestDetails.getRestOperationType();
        switch (operation) {
            case CREATE:
            case READ:
            case VREAD:
            case UPDATE:
            case SEARCH_TYPE:
            case TRANSACTION:
                return Optional.of(new Coding("http://hl7.org/fhir/restful-interaction",
                        operation.getCode(), operation.getCode()));
            default:
                return Optional.empty();
        }
    }

    private AuditEvent.AuditEventAgentComponent getAgent(Exchange exchange) {
        AuditEvent.AuditEventAgentComponent agent = new AuditEvent.AuditEventAgentComponent(new BooleanType(true))
                .setType(new CodeableConcept()
                        .addCoding(new Coding("http://dicom.nema.org/resources/ontology/DCM", "110153", "Source Role ID")));

        if (exchange.getIn().getHeader(Constants.HTTP_CLIENT_IP_ADDRESS) != null) {
            agent.setNetwork(new AuditEvent.AuditEventAgentNetworkComponent()
                    .setAddress(exchange.getIn().getHeader(Constants.HTTP_CLIENT_IP_ADDRESS, String.class))
                    .setType(AuditEvent.AuditEventAgentNetworkType._2));
        }

        return agent;
    }

    private Optional<AuditEvent.AuditEventEntityComponent> getEntity(Exchange exchange, RequestDetails requestDetails) {
        if (requestDetails.getRestOperationType() != RestOperationTypeEnum.CREATE &&
                requestDetails.getRestOperationType() != RestOperationTypeEnum.UPDATE &&
                requestDetails.getRestOperationType() != RestOperationTypeEnum.TRANSACTION) {
            return Optional.empty();
        }

        if (exchange.getProperty(CamelConstants.OUTCOME) == null) {
            return Optional.empty();
        }

        MethodOutcome outcome = exchange.getProperty(CamelConstants.OUTCOME, MethodOutcome.class);

        return Optional.of(new AuditEvent.AuditEventEntityComponent()
                .setWhat(new Reference(outcome.getId()))
                .setType(new Coding("http://terminology.hl7.org/CodeSystem/audit-entity-type", "2", "System Object")));
    }
}

