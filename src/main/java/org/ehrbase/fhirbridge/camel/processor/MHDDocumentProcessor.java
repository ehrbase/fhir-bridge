package org.ehrbase.fhirbridge.camel.processor;

import ca.uhn.fhir.rest.api.MethodOutcome;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component(MHDDocumentProcessor.BEAN_ID)
public class MHDDocumentProcessor implements Processor {

    public static final String BEAN_ID = "mhdDocumentProcessor";

    @Override
    public void process(Exchange exchange) throws Exception {
        MethodOutcome methodOutcome = exchange.getIn().getBody(MethodOutcome.class);
        /*Bundle responseBundle = new Bundle()
                .setType(Bundle.BundleType.TRANSACTIONRESPONSE)
                .addEntry(new Bundle.BundleEntryComponent()
                        .setResource((Resource) methodOutcome.getResource()));

        exchange.getMessage().setBody(responseBundle);*/
    }

}
