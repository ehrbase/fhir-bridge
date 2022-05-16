package org.ehrbase.fhirbridge.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component(ITI68Processor.BEAN_ID)

public class ITI68Processor implements Processor {

    public static final String BEAN_ID = "iti68Processor";

    @Override
    public void process(Exchange exchange) throws Exception {

    }
}
