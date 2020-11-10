package org.ehrbase.fhirbridge.camel.route;

import ca.uhn.fhir.jpa.api.dao.IFhirResourceDao;
import org.apache.camel.builder.RouteBuilder;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.springframework.stereotype.Component;

@Component
public class DiagnosticReportRoutes extends RouteBuilder {

    private final IFhirResourceDao<DiagnosticReport> diagnosticReportDao;

    public DiagnosticReportRoutes(IFhirResourceDao<DiagnosticReport> diagnosticReportDao) {
        this.diagnosticReportDao = diagnosticReportDao;
    }

    @Override
    public void configure() {
        // @formatter:off
        from("diag-rep-create:/service?audit=false")
            .routeId("create-diagnostic-report")
            .bean(diagnosticReportDao, "create(${body})")
            .to("log:create-diagnostic-report?showAll=true");

        from("diag-rep-read:/service?audit=false")
            .routeId("read-diagnostic-report-read")
            .to("log:read-diagnostic-report?showAll=true");
        // @formatter:on
    }
}
