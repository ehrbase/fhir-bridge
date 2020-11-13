package org.ehrbase.fhirbridge.camel;

import ca.uhn.fhir.jpa.api.dao.IFhirResourceDao;
import org.apache.camel.builder.RouteBuilder;
import org.ehrbase.fhirbridge.camel.processor.DefaultCreateResourceRequestValidator;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.springframework.stereotype.Component;

@Component
public class DiagnosticReportRoutes extends RouteBuilder {

    private final IFhirResourceDao<DiagnosticReport> diagnosticReportDao;

    private final DefaultCreateResourceRequestValidator requestValidator;

    public DiagnosticReportRoutes(IFhirResourceDao<DiagnosticReport> diagnosticReportDao,
                                  DefaultCreateResourceRequestValidator requestValidator) {
        this.diagnosticReportDao = diagnosticReportDao;
        this.requestValidator = requestValidator;
    }

    @Override
    public void configure() {
        // @formatter:off
        from("diag-rep-create:/service?audit=false&fhirContext=#fhirContext")
            .routeId("create-diagnostic-report")
            .process(requestValidator)
            .bean(diagnosticReportDao, "create(${body})")
            .to("log:create-diagnostic-report?showAll=true");

        from("diag-rep-read:/service?audit=false&fhirContext=#fhirContext")
            .routeId("read-diagnostic-report-read")
            .to("log:read-diagnostic-report?showAll=true");
        // @formatter:on
    }
}
