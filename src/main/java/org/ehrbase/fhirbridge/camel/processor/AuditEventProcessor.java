package org.ehrbase.fhirbridge.camel.processor;

import ca.uhn.fhir.jpa.api.dao.IFhirResourceDao;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.hl7.fhir.r4.model.AuditEvent;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.codesystems.AuditEventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class AuditEventProcessor implements Processor {

    private final Logger logger = LoggerFactory.getLogger(AuditEventProcessor.class);

    private final IFhirResourceDao<AuditEvent> auditEventDao;

    public AuditEventProcessor(IFhirResourceDao<AuditEvent> auditEventDao) {
        this.auditEventDao = auditEventDao;
    }

    @Override
    public void process(Exchange exchange) {
        logger.trace("Registering AuditEvent...");

        // @formatter:off
        AuditEvent auditEvent = new AuditEvent()
            .setType(getAuditEventType())
            .addSubtype(getAuditEventSubtype())
            .setAction(AuditEvent.AuditEventAction.C)
            .setRecorded(new Date());
//            .setOutcome()
//            .setOutcomeDesc()
//            .addAgent()
//            .addAgent()

        // @formatter:on

        auditEventDao.create(auditEvent);

        logger.debug("Created audit event: {}", auditEvent);
    }

    private Coding getAuditEventType() {
        return new Coding(AuditEventType.REST.getSystem(), AuditEventType.REST.toCode(), AuditEventType.REST.getDisplay());
    }

    private Coding getAuditEventSubtype() {
        return new Coding("http://hl7.org/fhir/restful-interaction", "create", "create");
    }
}
