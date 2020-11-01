package org.ehrbase.fhirbridge.camel.route;

import ca.uhn.fhir.rest.api.MethodOutcome;
import org.apache.camel.builder.RouteBuilder;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.springframework.stereotype.Component;

@Component
public class DiagnosticReportRoutes extends RouteBuilder {

    @Override
    public void configure() {
        // @formatter:off
        from("diagnostic-report-create:/service")
            .routeId("diagnosticReport-create")
//            .to("ehr:ehrbase?ehrEndpointType=composition")
            .process(exchange -> exchange.getMessage().setBody(new MethodOutcome()))
            .to("log:debug?showAll=true");

        from("diagnostic-report-read:/service")
            .routeId("diagnosticReport-read")
//            .to("ehr:ehrbase?ehrEndpointType=aql")
            .process(exchange -> exchange.getMessage().setBody(new DiagnosticReport()))
            .to("log:debug?showAll=true");
        // @formatter:on
    }
}
