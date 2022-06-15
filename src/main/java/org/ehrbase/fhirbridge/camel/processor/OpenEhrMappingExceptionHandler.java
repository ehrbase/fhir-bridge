package org.ehrbase.fhirbridge.camel.processor;

import ca.uhn.fhir.rest.api.MethodOutcome;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.ehrbase.fhirbridge.camel.CamelConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OpenEhrMappingExceptionHandler implements Processor {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenEhrMappingExceptionHandler.class);

    @Override
    public void process(Exchange exchange) {
        try {
            exchange.getMessage().setBody(exchange.getProperty(CamelConstants.OUTCOME, MethodOutcome.class));
            LOGGER.error("An error has occurred: {}", exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class).getMessage());
        } catch (Exception e) {
            LOGGER.error("An error has occurred while handling openEHR conversion exceptions: {}", e.getMessage());
        }
    }
}
