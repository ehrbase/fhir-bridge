package org.ehrbase.fhirbridge.camel.ehr;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EhrbaseProducer extends DefaultProducer {

    private final Logger logger = LoggerFactory.getLogger(EhrbaseProducer.class);

    public EhrbaseProducer(Endpoint endpoint) {
        super(endpoint);
    }

    @Override
    public void process(Exchange exchange) {
        logger.trace("[EhrbaseProducer] Start processing exchange: {}", exchange);
        // TODO: integrate DefaultRestClient
    }
}
