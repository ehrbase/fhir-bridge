package org.ehrbase.fhirbridge.camel.route;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.apache.camel.builder.RouteBuilder;
import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.ehrbase.client.exception.WrongStatusCodeException;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.fhirbridge.camel.CamelConstants;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.springframework.stereotype.Component;

@Component
public class CommonRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // @formatter:off

        from("direct:internal-provide-resource")
            .routeId("internal-provide-resource")
            .process("ehrIdLookupProcessor")
            .doTry()
                .to("bean:fhirResourceConversionService?method=convert(${headers.FhirBridgeProfile}, ${body})")
            .doCatch(ConversionException.class)
                .throwException(UnprocessableEntityException.class, "${exception.message}")
            .end()
            .to("direct:internal-provide-resource-after-converter");

       from("direct:internal-provide-resource-after-converter")
            .routeId("internal-provide-resource-after-converter-route")
            .choice()
                .when(header(CamelConstants.COMPOSITION_VERSION_UID).isNotNull())
                    .process(exchange -> {
                        var composition = exchange.getIn().getMandatoryBody(CompositionEntity.class);
                        composition.setVersionUid(new VersionUid(exchange.getIn().getHeader(CamelConstants.COMPOSITION_VERSION_UID, String.class)));
                    })
               .end()
            .doTry()
               .to("ehr-composition:producer?operation=mergeCompositionEntity")
            .doCatch(WrongStatusCodeException.class)
               .throwException(UnprocessableEntityException.class, "${exception.message}")
            .end()
            .process("provideResourceResponseProcessor");

        // @formatter:on
    }
}
