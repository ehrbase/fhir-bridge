package org.ehrbase.fhirbridge.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class EhrIdProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        System.out.println("test");
    }
}
