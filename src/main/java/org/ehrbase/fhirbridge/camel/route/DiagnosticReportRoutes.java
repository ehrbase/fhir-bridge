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
            .to("ehr:ehrbase?endpointType=composition")
            .process(exchange -> exchange.getMessage().setBody(new MethodOutcome()));


        from("diagnostic-report-read:/service")
            .routeId("diagnosticReport-read")
            .to("ehr:ehrbase?endpointType=aql")
            .process(exchange -> exchange.getMessage().setBody(new DiagnosticReport()));
        // @formatter:on
    }
}
