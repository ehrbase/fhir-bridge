package org.ehrbase.fhirbridge.camel.route;

import ca.uhn.fhir.jpa.api.dao.IFhirResourceDao;
import org.apache.camel.builder.RouteBuilder;
import org.ehrbase.fhirbridge.camel.FhirBridgeConstants;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConstants;
import org.ehrbase.fhirbridge.camel.processor.DefaultExceptionHandler;
import org.ehrbase.fhirbridge.camel.processor.EhrIdLookupProcessor;
import org.ehrbase.fhirbridge.camel.processor.ResourceProfileValidator;
import org.ehrbase.fhirbridge.camel.processor.ResourceResponseProcessor;
import org.ehrbase.fhirbridge.ehr.converter.CompositionConverterResolver;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.stereotype.Component;

@Component
public class ObservationRoutes extends RouteBuilder {

    private final IFhirResourceDao<Observation> observationDao;

    private final EhrIdLookupProcessor ehrIdLookupProcessor;

    private final ResourceResponseProcessor resourceResponseProcessor;

    private final ResourceProfileValidator requestValidator;

    private final CompositionConverterResolver compositionConverterResolver;

    private final DefaultExceptionHandler defaultExceptionHandler;

    public ObservationRoutes(IFhirResourceDao<Observation> observationDao,
                             EhrIdLookupProcessor ehrIdLookupProcessor,
                             ResourceResponseProcessor resourceResponseProcessor,
                             ResourceProfileValidator requestValidator,
                             CompositionConverterResolver compositionConverterResolver,
                             DefaultExceptionHandler defaultExceptionHandler) {
        this.observationDao = observationDao;
        this.ehrIdLookupProcessor = ehrIdLookupProcessor;
        this.resourceResponseProcessor = resourceResponseProcessor;
        this.requestValidator = requestValidator;
        this.compositionConverterResolver = compositionConverterResolver;
        this.defaultExceptionHandler = defaultExceptionHandler;
    }

    @Override
    public void configure() {
        // @formatter:off
        onException(Exception.class)
                .process(defaultExceptionHandler);

        from("fhir-create-observation:fhirConsumer?fhirContext=#fhirContext")
            .onCompletion()
                .process("auditCreateResourceProcessor")
            .end()
            .process(requestValidator)
            .to("direct:process-observation");

        from("direct:process-observation")
            .setHeader(FhirBridgeConstants.METHOD_OUTCOME, method(observationDao, "create"))
            .process(ehrIdLookupProcessor)
            .setHeader(CompositionConstants.COMPOSITION_CONVERTER, method(compositionConverterResolver, "resolve(${header.FhirBridgeProfile})"))
            .to("ehr-composition:compositionEndpoint?operation=mergeCompositionEntity")
            .process(resourceResponseProcessor);
        // @formatter:on
    }
}
