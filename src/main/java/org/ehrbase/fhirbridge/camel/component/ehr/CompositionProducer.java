package org.ehrbase.fhirbridge.camel.component.ehr;

import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultProducer;
import org.ehrbase.fhirbridge.ehr.Composition;
import org.ehrbase.fhirbridge.ehr.converter.CompositionConverter;
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

    private void mergeCompositionEntity(org.ehrbase.client.openehrclient.CompositionEndpoint compositionEndpoint, Exchange exchange) {
        logger.trace("Merge composition...");

        Object body = exchange.getIn().getBody();
        if (body == null) {
            throw new IllegalArgumentException("Body must not be null");
        }

        CompositionConverter converter = determineConverter(exchange);
        if (converter != null) {
            body = converter.toComposition(body);
        }

        Object mergedComposition = compositionEndpoint.mergeCompositionEntity(body);
        if (converter != null) {
            mergedComposition = converter.fromComposition((Composition) mergedComposition);
        }

        exchange.getMessage().setBody(mergedComposition);
    }

    private void find(org.ehrbase.client.openehrclient.CompositionEndpoint compositionEndpoint, Exchange exchange) {
        logger.trace("Find composition...");

//        compositionEndpoint.find()
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private CompositionOperation determineOperation(Exchange exchange) {
        CompositionOperation operation = exchange.getIn().getHeader(CompositionConstants.OPERATION, CompositionOperation.class);
        if (operation == null) {
            operation = endpoint.getConfiguration().getOperation();
        }
        return operation;
    }

    private CompositionConverter determineConverter(Exchange exchange) {
        CompositionConverter converter = exchange.getIn().getHeader(CompositionConstants.COMPOSITION_CONVERTER, CompositionConverter.class);
        if (converter == null) {
            converter = endpoint.getConfiguration().getConverter();
        }
        return converter;
    }
}
