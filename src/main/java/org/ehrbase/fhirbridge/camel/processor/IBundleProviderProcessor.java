package org.ehrbase.fhirbridge.camel.processor;

import ca.uhn.fhir.rest.api.server.IBundleProvider;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.openehealth.ipf.commons.ihe.fhir.Constants;

/**
 * @since 1.0.0
 */
public class IBundleProviderProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        IBundleProvider bundleProvider = exchange.getIn().getBody(IBundleProvider.class);

        if (exchange.getIn().getHeaders().containsKey(Constants.FHIR_REQUEST_SIZE_ONLY)) {
            exchange.getMessage().setHeader(Constants.FHIR_REQUEST_SIZE_ONLY, bundleProvider.size());
        } else {
            Integer from = exchange.getIn().getHeader(Constants.FHIR_FROM_INDEX, Integer.class);
            Integer to = exchange.getIn().getHeader(Constants.FHIR_TO_INDEX, Integer.class);
            exchange.getMessage().setBody(bundleProvider.getResources(from, to));
        }
    }
}
