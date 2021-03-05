package org.ehrbase.fhirbridge.camel.route;

import ca.uhn.fhir.jpa.api.dao.IFhirResourceDao;
import org.ehrbase.fhirbridge.camel.FhirBridgeConstants;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConstants;
import org.ehrbase.fhirbridge.camel.processor.DefaultExceptionHandler;
import org.ehrbase.fhirbridge.camel.processor.EhrIdLookupProcessor;
import org.ehrbase.fhirbridge.camel.processor.ResourceProfileValidator;
import org.ehrbase.fhirbridge.camel.processor.ResourceResponseProcessor;
import org.ehrbase.fhirbridge.ehr.converter.CompositionConverterResolver;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.springframework.stereotype.Component;

@Component
public class DiagnosticReportRoutes extends AbstractRouteBuilder {

    private final IFhirResourceDao<DiagnosticReport> diagnosticReportDao;

    private final EhrIdLookupProcessor ehrIdLookupProcessor;

    private final ResourceResponseProcessor resourceResponseProcessor;

    private final CompositionConverterResolver compositionConverterResolver;

    private final ResourceProfileValidator requestValidator;

    private final DefaultExceptionHandler defaultExceptionHandler;


    public DiagnosticReportRoutes(IFhirResourceDao<DiagnosticReport> diagnosticReportDao,
                                  EhrIdLookupProcessor ehrIdLookupProcessor,
                                  ResourceResponseProcessor resourceResponseProcessor,
                                  CompositionConverterResolver compositionConverterResolver,
                                  ResourceProfileValidator requestValidator,
                                  DefaultExceptionHandler defaultExceptionHandler) {
        this.diagnosticReportDao = diagnosticReportDao;
        this.ehrIdLookupProcessor = ehrIdLookupProcessor;
        this.resourceResponseProcessor = resourceResponseProcessor;
        this.requestValidator = requestValidator;
        this.defaultExceptionHandler = defaultExceptionHandler;
        this.compositionConverterResolver = compositionConverterResolver;
    }


    @Override
    public void configure() {
        // @formatter:off
        onException(Exception.class)
            .process(defaultExceptionHandler);

        from("diagnostic-report-create:fhirConsumer?fhirContext=#fhirContext")
            .onCompletion()
                .process("auditCreateResourceProcessor")
            .end()
            .process(requestValidator)
            .to("direct:process-diagnostic-report");

        // 'Find Diagnostic Report' route definition

        from("diagnostic-report-find:consumer?fhirContext=#fhirContext&lazyLoadBundles=true")
            .choice()
                .when(isSearchOperation())
                    .to("bean:diagnosticReportDao?method=search(${body}, ${headers.FhirRequestDetails})")
                    .process("bundleProviderResponseProcessor")
                .otherwise()
                    .to("bean:diagnosticReportDao?method=read(${body}, ${headers.FhirRequestDetails})");

        // Internal routes definition

        from("direct:process-diagnostic-report")
            .setHeader(FhirBridgeConstants.METHOD_OUTCOME, method(diagnosticReportDao, "create"))
            .process(ehrIdLookupProcessor)
            .setHeader(CompositionConstants.COMPOSITION_CONVERTER, method(compositionConverterResolver, "resolve(${header.FhirBridgeProfile})"))
            .to("ehr-composition:compositionProducer?operation=mergeCompositionEntity")
            .process(resourceResponseProcessor);

        // @formatter:on
    }
}
