package org.ehrbase.fhirbridge;

import org.apache.camel.builder.RouteBuilder;
import org.hl7.fhir.r4.model.AuditEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DefaultFhirRouteBuilder extends RouteBuilder {

    @Override
    public void configure() {
        // @formatter:off
//        from("fhir://create/resource?serverUrl=/test&resource=#myAuditEvent&preferReturn=OPERATION_OUTCOME")
//            .to("log://FHIR-LOGGER?showAll=true");

//        from("direct://test")
//            .to("fhir://create/resource?serverUrl=http://localhost:8080/hapi-fhir&inBody=resourceAsString");
        // @formatter:on
    }

    @Bean
    public AuditEvent myAuditEvent() {
        return new AuditEvent();
    }
}
