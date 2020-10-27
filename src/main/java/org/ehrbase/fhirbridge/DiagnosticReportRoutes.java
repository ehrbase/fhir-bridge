package org.ehrbase.fhirbridge;

import ca.uhn.fhir.rest.api.MethodOutcome;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class DiagnosticReportRoutes extends RouteBuilder {

    @Override
    public void configure() {
        // @formatter:off
        from("diagnostic-report-create://service?audit=false")
            .routeId("diagnosticReport-create")
            .process(exchange -> exchange.getMessage().setBody(new MethodOutcome()))
            .to("log:debug?showAll=true");
        // @formatter:on
    }
}
