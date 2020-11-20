package org.ehrbase.fhirbridge.camel.route;

import ca.uhn.fhir.jpa.api.dao.IFhirResourceDao;
import org.apache.camel.builder.RouteBuilder;
import org.ehrbase.fhirbridge.camel.DefaultCreateResourceRequestValidator;
import org.ehrbase.fhirbridge.camel.FhirBridgeConstants;
import org.ehrbase.fhirbridge.camel.PatientIdProcessor;
import org.ehrbase.fhirbridge.ehr.converter.DiagnosticReportLabCompositionConverter;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DiagnosticReportRoutes extends RouteBuilder {

    private final IFhirResourceDao<DiagnosticReport> diagnosticReportDao;

    private final DefaultCreateResourceRequestValidator requestValidator;

    private final PatientIdProcessor patientIdProcessor;

    public DiagnosticReportRoutes(IFhirResourceDao<DiagnosticReport> diagnosticReportDao,
                                  DefaultCreateResourceRequestValidator requestValidator,
                                  PatientIdProcessor patientIdProcessor) {
        this.diagnosticReportDao = diagnosticReportDao;
        this.requestValidator = requestValidator;
        this.patientIdProcessor = patientIdProcessor;
    }

    @Override
    public void configure() {
        // @formatter:off
        from("fhir-create-diagnostic-report:fhirConsumer?fhirContext=#fhirContext")
            .process(requestValidator)
            .bean(diagnosticReportDao, "create(${body})")
            .setHeader(FhirBridgeConstants.METHOD_OUTCOME, body())
            .setBody(simple("${body.resource}"))
            .process(patientIdProcessor)
            .to("ehr-composition:compositionProducer?operation=mergeCompositionEntity&compositionConverter=#diagnosticReportLabCompositionConverter")
            .setBody(header(FhirBridgeConstants.METHOD_OUTCOME));
        // @formatter:on
    }

    @Bean
    public DiagnosticReportLabCompositionConverter diagnosticReportLabCompositionConverter() {
        return new DiagnosticReportLabCompositionConverter();
    }
}
