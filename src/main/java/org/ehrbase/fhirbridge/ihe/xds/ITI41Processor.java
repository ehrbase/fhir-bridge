package org.ehrbase.fhirbridge.ihe.xds;

import ca.uhn.fhir.context.FhirContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ITI41Processor  implements Processor {

    FhirContext ctx = FhirContext.forR4();
    public static final String BEAN_ID = "iti41Processor";

    @Override
    public void process(Exchange exchange) throws Exception {
        exchange.getIn().getHeader("input_bundle");
    }
}
