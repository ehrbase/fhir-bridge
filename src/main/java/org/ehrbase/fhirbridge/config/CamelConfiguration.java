package org.ehrbase.fhirbridge.config;

import ca.uhn.fhir.jpa.api.dao.IFhirResourceDao;
import org.ehrbase.fhirbridge.camel.processor.AuditEventProcessor;
import org.hl7.fhir.r4.model.AuditEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamelConfiguration {

    @Bean
    public AuditEventProcessor internalAuditProcessor(IFhirResourceDao<AuditEvent> auditEventDao) {
        return new AuditEventProcessor(auditEventDao);
    }
}
