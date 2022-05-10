package org.ehrbase.fhirbridge.camel.route;


import org.apache.camel.builder.RouteBuilder;
import org.ehrbase.fhirbridge.camel.processor.MHDDocumentProcessor;
import org.springframework.stereotype.Component;

@Component
public class MHDRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        // @formatter:off
        from("mhd-iti65://service?audit=true")
                .process(MHDDocumentProcessor.BEAN_ID);
    }
    // @formatter:on

}
