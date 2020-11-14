package org.ehrbase.fhirbridge.camel;

import ca.uhn.fhir.jpa.api.dao.IFhirResourceDao;
import ca.uhn.fhir.rest.api.MethodOutcome;
import org.apache.camel.builder.RouteBuilder;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.fhirbridge.camel.processor.DefaultCreateResourceRequestValidator;
import org.ehrbase.fhirbridge.camel.processor.PatientIdProcessor;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.GECCOLaborbefundComposition;
import org.ehrbase.fhirbridge.mapping.FhirDiagnosticReportOpenehrLabResults;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.springframework.stereotype.Component;

@Component
public class DiagnosticReportRoutes extends RouteBuilder {

    private final IFhirResourceDao<DiagnosticReport> diagnosticReportDao;

    private final DefaultCreateResourceRequestValidator requestValidator;

    private final PatientIdProcessor patientIdProcessor;

    private final OpenEhrClient openEhrClient;

    public DiagnosticReportRoutes(IFhirResourceDao<DiagnosticReport> diagnosticReportDao,
                                  DefaultCreateResourceRequestValidator requestValidator,
                                  PatientIdProcessor patientIdProcessor,
                                  OpenEhrClient openEhrClient) {
        this.diagnosticReportDao = diagnosticReportDao;
        this.requestValidator = requestValidator;
        this.patientIdProcessor = patientIdProcessor;
        this.openEhrClient = openEhrClient;
    }

    @Override
    public void configure() {
        // @formatter:off
        from("diag-rep-create:/service?audit=false&fhirContext=#fhirContext")
            .routeId("create-diagnostic-report")
            .process(requestValidator)
            .bean(diagnosticReportDao, "create(${body})")
            .setBody(simple("${body.resource}"))
            .process(patientIdProcessor)
            .process(exchange -> {
                DiagnosticReport diagnosticReport = exchange.getIn().getBody(DiagnosticReport.class);
                exchange.getMessage().setBody(FhirDiagnosticReportOpenehrLabResults.map(diagnosticReport));
            })
            .to("ehr-composition:/test?operation=mergeCompositionEntity&")
            .process(exchange -> {
                GECCOLaborbefundComposition composition = exchange.getIn().getBody(GECCOLaborbefundComposition.class);
                exchange.getMessage().setBody(FhirDiagnosticReportOpenehrLabResults.map(composition));
            })
            .process(exchange -> {
                MethodOutcome methodOutcome = new MethodOutcome();
                methodOutcome.setResource(exchange.getIn().getBody(IBaseResource.class));
                exchange.getMessage().setBody(methodOutcome);
            });
        // @formatter:on

        from("diag-rep-read:/service?audit=false&fhirContext=#fhirContext")
            .routeId("read-diagnostic-report-read")
            .to("log:read-diagnostic-report?showAll=true");
    }
}
