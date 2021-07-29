package org.ehrbase.fhirbridge.camel.route;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.util.ObjectHelper;
import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.fhirbridge.camel.CamelConstants;
import org.ehrbase.fhirbridge.camel.processor.EhrLookupProcessor;
import org.ehrbase.fhirbridge.camel.processor.FhirProfileValidator;
import org.ehrbase.fhirbridge.camel.processor.PatientReferenceProcessor;
import org.ehrbase.fhirbridge.camel.processor.ProvideResourceAuditHandler;
import org.ehrbase.fhirbridge.camel.processor.ResourcePersistenceProcessor;
import org.springframework.stereotype.Component;

@Component
public class CommonRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // @formatter:off

        // Provide resource
        from("direct:provideResource")
            .routeId("provideResourceRoute")
            .onCompletion()
                .process(ProvideResourceAuditHandler.BEAN_ID)
            .end()
            .process(FhirProfileValidator.BEAN_ID)
            .process(PatientReferenceProcessor.BEAN_ID)
            .process(ResourcePersistenceProcessor.BEAN_ID)
            .process(EhrLookupProcessor.BEAN_ID)
            .doTry()
                .to("bean:fhirResourceConversionService?method=convert(${headers.CamelFhirBridgeProfile}, ${body})")
                .process(exchange -> {
                    if (ObjectHelper.isNotEmpty(exchange.getIn().getHeader(CamelConstants.COMPOSITION_ID))) {
                        String compositionId = exchange.getIn().getHeader(CamelConstants.COMPOSITION_ID, String.class);
                        exchange.getIn().getBody(CompositionEntity.class).setVersionUid(new VersionUid(compositionId));
                    }
                })
                .to("ehr-composition:compositionProducer?operation=mergeCompositionEntity")
            .doCatch(Exception.class)
                .throwException(UnprocessableEntityException.class, "${exception.message}")
            .end()
            .process("provideResourceResponseProcessor");

        // @formatter:on
    }
}
