package org.ehrbase.fhirbridge.camel.processor;

import ca.uhn.fhir.jpa.api.dao.IFhirResourceDao;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.ehrbase.fhirbridge.camel.CamelConstants;
import org.hl7.fhir.r4.model.AuditEvent;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Reference;
import org.openehealth.ipf.commons.ihe.fhir.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * {@link Processor} that registers an {@link AuditEvent} in the database for each "Provide [resource type]"
 * transaction.
 *
 * @since 1.0.0
 */
@Component(ProvideResourceAuditHandler.BEAN_ID)
@SuppressWarnings("java:S6212")
public class ProvideResourceAuditHandler implements Processor {

    public static final String BEAN_ID = "provideResourceAuditHandler";

    private final IFhirResourceDao<AuditEvent> auditEventDao;

    @Value("${spring.application.name}")
    private String applicationName;

    public ProvideResourceAuditHandler(IFhirResourceDao<AuditEvent> auditEventDao) {
        this.auditEventDao = auditEventDao;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        AuditEvent auditEvent = new AuditEvent()
                .setType(new Coding("http://terminology.hl7.org/CodeSystem/audit-event-type", "rest", "RESTful Operation"))
                .addSubtype(new Coding("http://hl7.org/fhir/restful-interaction", "create", "create"))
                .setAction(AuditEvent.AuditEventAction.C)
                .setRecorded(new Date());

        Throwable exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Throwable.class);
        if (exception == null) {
            auditEvent.setOutcome(AuditEvent.AuditEventOutcome._0);
            auditEvent.setOutcomeDesc("Resource created successfully");
        } else {
            auditEvent.setOutcome(AuditEvent.AuditEventOutcome._12);
            auditEvent.setOutcomeDesc(exception.getMessage());
        }

        auditEvent.setSource(source());
        auditEvent.addEntity(entity(exchange));

        auditEventDao.create(auditEvent);
    }

    private AuditEvent.AuditEventSourceComponent source() {
        return new AuditEvent.AuditEventSourceComponent()
                .setObserver(new Reference()
                        .setIdentifier(new Identifier()
                                .setValue(applicationName)))
                .addType(new Coding()
                        .setSystem("DCM")
                        .setCode("4")
                        .setDisplay("Application Server Process or Thread"));
    }

    private AuditEvent.AuditEventEntityComponent entity(Exchange exchange) {
        MethodOutcome outcome = exchange.getProperty(CamelConstants.OUTCOME, MethodOutcome.class);
        RequestDetails requestDetails = exchange.getIn().getHeader(Constants.FHIR_REQUEST_DETAILS, RequestDetails.class);

        return new AuditEvent.AuditEventEntityComponent()
                .setWhat(new Reference(outcome.getId()))
                .setType(new Coding()
                        .setSystem("http://hl7.org/fhir/resource-types")
                        .setCode(requestDetails.getResourceName())
                        .setDisplay(requestDetails.getResourceName()));
    }
}
