package org.ehrbase.fhirbridge.camel.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;

public abstract class AbstractRouteBuilder extends RouteBuilder {

    @Value("${fhir-bridge.debug.enabled:false}")
    private boolean debug;

    @Override
    public void configure() throws Exception {
        errorHandler(defaultErrorHandler()
                .logStackTrace(debug)
                .logExhaustedMessageHistory(debug));

        onException(Exception.class)
                .process("defaultExceptionHandler");
    }
}
