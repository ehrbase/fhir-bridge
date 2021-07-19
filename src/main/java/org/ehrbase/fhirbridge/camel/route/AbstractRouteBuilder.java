package org.ehrbase.fhirbridge.camel.route;

import org.apache.camel.builder.RouteBuilder;

public abstract class AbstractRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        errorHandler(defaultErrorHandler()
                .logExhaustedMessageHistory(false));
    }
}
