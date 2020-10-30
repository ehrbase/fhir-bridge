package org.ehrbase.fhirbridge.camel.fhir;

import ca.uhn.fhir.rest.api.MethodOutcome;
import org.apache.camel.builder.RouteBuilder;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.springframework.stereotype.Component;

@Component
public class DiagnosticReportRoutes extends RouteBuilder {

    @Override
    public void configure() {
        // @formatter:off
        from("diagnostic-report-create://service")
            .routeId("diagnosticReport-create")
            .process(exchange -> exchange.getMessage().setBody(new MethodOutcome()))
            .to("log:DiagnosticReport-Create?showAll=true");

        from("diagnostic-report-read://service")
                .routeId("diagnosticReport-read")
                .process(exchange -> exchange.getMessage().setBody(new DiagnosticReport()))
                .to("log:DiagnosticReport-Read?showAll=true");
        // @formatter:on
    }
}
