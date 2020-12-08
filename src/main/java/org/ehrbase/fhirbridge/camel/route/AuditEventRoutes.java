package org.ehrbase.fhirbridge.camel.route;

import ca.uhn.fhir.jpa.search.PersistedJpaBundleProvider;
import ca.uhn.fhir.rest.api.server.IBundleProvider;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

import static org.openehealth.ipf.commons.ihe.fhir.Constants.FHIR_REQUEST_SIZE_ONLY;

@Component
public class AuditEventRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // @formatter:off
        from("fhir-find-audit-event:fhirConsumer?fhirContext=#fhirContext&lazyLoadBundles=true")
            .to("bean:myAuditEventDaoR4?method=search(${body})")
            .process(exchange -> {
                IBundleProvider bundleProvider = exchange.getIn().getBody(PersistedJpaBundleProvider.class);
                Map<String, Object> headers = exchange.getIn().getHeaders();
                if (headers.containsKey(FHIR_REQUEST_SIZE_ONLY)) {
                    exchange.getMessage().setHeader(FHIR_REQUEST_SIZE_ONLY, bundleProvider.size());
                } else {

                }
            });
        // @formatter:on
    }
}
