package org.ehrbase.fhirbridge.camel.processor;

import ca.uhn.fhir.jpa.api.dao.IFhirResourceDao;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.hl7.fhir.r4.model.AuditEvent;
import org.hl7.fhir.r4.model.Coding;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AuditCreateResourceProcessor implements Processor {

    private final IFhirResourceDao<AuditEvent> auditEventDao;

    public AuditCreateResourceProcessor(IFhirResourceDao<AuditEvent> auditEventDao) {
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
        } else {
            auditEvent.setOutcome(AuditEvent.AuditEventOutcome._12);
            auditEvent.setOutcomeDesc(exception.getMessage());
        }

        auditEventDao.create(auditEvent);
    }
}
