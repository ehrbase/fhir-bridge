package org.ehrbase.fhirbridge.camel.route;

import org.ehrbase.fhirbridge.camel.FhirBridgeConstants;

public class ImmunizationRoutes extends AbstractRouteBuilder {

    @Override
    public void configure() throws Exception {
        // @formatter:off
        super.configure();

        // 'Create Diagnostic Report' route definition
        from("immunization-create:consumer?fhirContext=#fhirContext")
                .onCompletion()
                .process("auditCreateResourceProcessor")
                .end()
                .process("resourceProfileValidator")
                .to("direct:process-immunization");

        // 'Find Diagnostic Report' route definition
        from("immunization-find:consumer?fhirContext=#fhirContext&lazyLoadBundles=true")
                .choice()
                .when(isSearchOperation())
                .to("bean:immunizationDao?method=search(${body}, ${headers.FhirRequestDetails})")
                .process("bundleProviderResponseProcessor")
                .otherwise()
                .to("bean:immunizationDao?method=read(${body}, ${headers.FhirRequestDetails})");

        // Internal routes definition
        from("direct:process-immunization")
                .setHeader(FhirBridgeConstants.METHOD_OUTCOME, method("immunizationDao", "create(${body}, ${headers.FhirRequestDetails})"))
                .process("ehrIdLookupProcessor")
                .to("bean:fhirResourceConversionService?method=convert(${headers.FhirBridgeProfile}, ${body})")
                .to("ehr-composition:compositionProducer?operation=mergeCompositionEntity")
                .process("resourceResponseProcessor");
    }
}