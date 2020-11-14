package org.ehrbase.fhirbridge.camel.component.ehr;

import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AqlProducer extends DefaultProducer {

    private final Logger logger = LoggerFactory.getLogger(AqlProducer.class);

    private final AqlEndpoint endpoint;

    public AqlProducer(AqlEndpoint endpoint) {
        super(endpoint);
        this.endpoint = endpoint;
    }

    @Override
    public void process(Exchange exchange) {
        logger.debug("Start processing exchange: {}", exchange);

    }
}
