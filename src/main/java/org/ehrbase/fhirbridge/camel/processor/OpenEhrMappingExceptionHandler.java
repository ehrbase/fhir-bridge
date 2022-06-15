package org.ehrbase.fhirbridge.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OpenEhrMappingExceptionHandler implements Processor {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenEhrMappingExceptionHandler.class);

    @Override
    public void process(Exchange exchange) {
        try {
            LOGGER.error("An error has occurred: {}", exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class).getMessage());
        } catch (Exception e) {
            LOGGER.error("Cannot log error message: {}", e.getMessage());
        }
    }
}
