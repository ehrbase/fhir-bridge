package org.ehrbase.fhirbridge.ihe.xds;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;

public class ITI41Processor implements Processor {

    public static final String BEAN_ID = "iti41Processor";

    @Override
    public void process(Exchange exchange) throws Exception {
        ITITrace iti41Trace = (ITITrace) exchange.getIn().getHeader("iti65-trace");
        iti41Trace.setCompositionEntity((CompositionEntity) exchange.getIn().getBody());
        exchange.getIn().setBody(iti41Trace);
    }
}
