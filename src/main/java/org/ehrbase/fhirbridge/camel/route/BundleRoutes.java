package org.ehrbase.fhirbridge.camel.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class BundleRoutes extends RouteBuilder {

    @Override
    public void configure() {
        // @formatter:off
        from("fhir-provide-bundle:fhirConsumer?fhirContext=#fhirContext")
            .process(exchange -> {
                System.out.println("TEST");
            });
        // @formatter:on
    }
}
