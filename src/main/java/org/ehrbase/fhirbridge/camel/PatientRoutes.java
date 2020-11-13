package org.ehrbase.fhirbridge.camel;

import org.apache.camel.builder.RouteBuilder;
import org.ehrbase.fhirbridge.camel.processor.DefaultCreateResourceRequestValidator;
import org.springframework.stereotype.Component;

@Component
public class PatientRoutes extends RouteBuilder {

    private final DefaultCreateResourceRequestValidator requestValidator;

    public PatientRoutes(DefaultCreateResourceRequestValidator requestValidator) {
        this.requestValidator = requestValidator;
    }

    @Override
    public void configure() {
        // @formatter:off
        from("pt-create:/service?audit=false&fhirContext=#fhirContext")
            .routeId("create-patient")
            .process(requestValidator)
            .to("log:create-patient?showAll=true");
        // @formatter:on
    }
}
