package org.ehrbase.fhirbridge.camel.component.ehr;

import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class CompositionProducer extends DefaultProducer {

    private final Logger logger = LoggerFactory.getLogger(CompositionProducer.class);

    private final CompositionEndpoint endpoint;

    public CompositionProducer(CompositionEndpoint endpoint) {
        super(endpoint);
        this.endpoint = endpoint;
    }

    @Override
    public void process(Exchange exchange) {
        UUID ehrId = exchange.getIn().getHeader(CompositionConstants.EHR_ID, UUID.class);
        if (ehrId == null) {
            throw new IllegalArgumentException("EhrId must not be null");
        }

        org.ehrbase.client.openehrclient.CompositionEndpoint compositionEndpoint = endpoint.getOpenEhrClient().compositionEndpoint(ehrId);
        CompositionOperation operation = determineOperation(exchange);

        if (operation == CompositionOperation.mergeCompositionEntity) {
            mergeCompositionEntity(compositionEndpoint, exchange);
        } else if (operation == CompositionOperation.find) {
            find(compositionEndpoint, exchange);
        } else {
            throw new IllegalArgumentException("Unsupported operation");
        }
    }

    private CompositionOperation determineOperation(Exchange exchange) {
        CompositionOperation operation = exchange.getIn().getHeader(CompositionConstants.OPERATION, CompositionOperation.class);
        if (operation == null) {
            operation = endpoint.getConfiguration().getOperation();
        }
        return operation;
    }

    private void mergeCompositionEntity(org.ehrbase.client.openehrclient.CompositionEndpoint compositionEndpoint, Exchange exchange) {
        logger.trace("Merge composition...");

        Object body = exchange.getIn().getBody();
        if (body == null) {
            throw new IllegalArgumentException("body must not be null");
        }

        Object mergedComposition = compositionEndpoint.mergeCompositionEntity(body);
        exchange.getMessage().setBody(mergedComposition);
    }

    private void find(org.ehrbase.client.openehrclient.CompositionEndpoint compositionEndpoint, Exchange exchange) {
        logger.trace("Find composition...");

//        compositionEndpoint.find()
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
