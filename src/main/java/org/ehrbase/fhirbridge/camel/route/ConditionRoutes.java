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
import org.hl7.fhir.r4.model.Condition;
import org.springframework.stereotype.Component;

/**
 * Implementation of {@link RouteBuilder} that provides route definitions for transactions
 * linked to {@link Condition} resource.
 *
 * @since 1.0.0
 */
@Component
public class ConditionRoutes extends AbstractRouteBuilder {

    private final IFhirResourceDao<Condition> conditionDao;

    private final ResourceProfileValidator requestValidator;

    private final EhrIdLookupProcessor ehrIdLookupProcessor;

    private final CompositionConverterResolver compositionConverterResolver;

    private final DefaultExceptionHandler defaultExceptionHandler;

    public ConditionRoutes(IFhirResourceDao<Condition> conditionDao,
                           ResourceProfileValidator requestValidator,
                           EhrIdLookupProcessor ehrIdLookupProcessor,
                           CompositionConverterResolver compositionConverterResolver,
                           DefaultExceptionHandler defaultExceptionHandler) {

        this.conditionDao = conditionDao;
        this.requestValidator = requestValidator;
        this.ehrIdLookupProcessor = ehrIdLookupProcessor;
        this.compositionConverterResolver = compositionConverterResolver;
        this.defaultExceptionHandler = defaultExceptionHandler;
    }

    @Override
    public void configure() {
        // @formatter:off

        // 'Create Condition' route definition

        from("condition-create:consumer?fhirContext=#fhirContext")
            .onCompletion()
                .process("auditCreateResourceProcessor")
            .end()
            .onException(Exception.class)
                .process(defaultExceptionHandler)
            .end()
            .process(requestValidator)
            .bean(conditionDao, "create(${body})")
            .setHeader(FhirBridgeConstants.METHOD_OUTCOME, body())
            .setBody(simple("${body.resource}"))
            .process(ehrIdLookupProcessor)
            .setHeader(CompositionConstants.COMPOSITION_CONVERTER, method(compositionConverterResolver, "resolve(${header.FhirBridgeProfile})"))
            .to("ehr-composition:compositionProducer?operation=mergeCompositionEntity")
            .process(new ResourceResponseProcessor());

        // 'Find Condition' route definition

        from("condition-find:consumer?fhirContext=#fhirContext&lazyLoadBundles=true")
            .choice()
                .when(isSearchOperation())
                    .to("bean:conditionDao?method=search(${body}, ${headers.FhirRequestDetails})")
                    .process("bundleProviderResponseProcessor")
                .otherwise()
                    .to("bean:conditionDao?method=read(${body}, ${headers.FhirRequestDetails})");

        // @formatter:on
    }
}
