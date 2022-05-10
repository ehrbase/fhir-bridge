package org.ehrbase.fhirbridge.camel.route;


import org.apache.camel.builder.RouteBuilder;
import org.ehrbase.fhirbridge.camel.processor.MHDDocumentProcessor;

public class MHDRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        // @formatter:off
        from("mhd-iti68://service?audit=true&secure=true")
                .process(MHDDocumentProcessor.BEAN_ID);
    }
    // @formatter:on

}
