package org.ehrbase.fhirbridge.camel.processor;

import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.server.exceptions.InternalErrorException;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Resource;
import org.springframework.stereotype.Component;

/**
 * {@link Processor} that creates the bundle to send as transaction response.
 */
@Component
public class BundleResponseProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        Bundle responseBundle = new Bundle()
                .setType(Bundle.BundleType.TRANSACTIONRESPONSE)
                .addEntry(
                        new Bundle.BundleEntryComponent()
                                .setResource((Resource) getMethodOutcome(exchange).getResource()));

        exchange.getMessage().setBody(responseBundle);
    }

    private MethodOutcome getMethodOutcome(Exchange exchange) {
        MethodOutcome methodOutcome = exchange.getIn().getBody(MethodOutcome.class);
        if (methodOutcome == null) {
            throw new InternalErrorException("MethodOutcome must not be null");
        }
        return methodOutcome;
    }
}
