package org.ehrbase.fhirbridge.camel.component.ehr.composition;

import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultProducer;
import org.ehrbase.client.openehrclient.OpenEhrClient;
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
        UUID ehrId = determineEhrId(exchange);
        if (ehrId == null) {
            throw new IllegalArgumentException("EhrId must not be null");
        }

        CompositionOperation operation = determineOperation(exchange);
        if (operation == CompositionOperation.mergeCompositionEntity) {
            mergeCompositionEntity(ehrId, exchange);
        } else if (operation == CompositionOperation.find) {
            find(ehrId, exchange);
        } else {
            throw new IllegalArgumentException("Unsupported operation");
        }
    }

    private void mergeCompositionEntity(UUID ehrId, Exchange exchange) {
        logger.trace("Merge composition...");

        Object body = exchange.getIn().getBody();
        if (body == null) {
            throw new IllegalArgumentException("Body must not be null");
        }

        CompositionConverter converter = determineConverter(exchange);
        if (converter != null) {
            body = converter.toComposition(body);
        }

        Object mergedComposition = getOpenEhrClient().compositionEndpoint(ehrId).mergeCompositionEntity(body);
        if (converter != null) {
            mergedComposition = converter.fromComposition((Composition) mergedComposition);
        }

        exchange.getMessage().setBody(mergedComposition);
    }

    private void find(UUID ehrId, Exchange exchange) {
        UUID compositionId = determineCompositionId(exchange);
        Class<?> expectedType = determineExpectedType(exchange);

        Object result = getOpenEhrClient().compositionEndpoint(ehrId)
                .find(compositionId, expectedType)
                .orElse(null);

        CompositionConverter converter = determineConverter(exchange);
        if (converter != null && result instanceof Composition) {
            exchange.getMessage().setBody(converter.fromComposition((Composition) result));
        } else {
            exchange.getMessage().setBody(result);
        }
    }

    private UUID determineEhrId(Exchange exchange) {
        UUID ehrId = exchange.getIn().getHeader(CompositionConstants.EHR_ID, UUID.class);
        if (ehrId == null) {
            ehrId = endpoint.getEhrId();
        }
        return ehrId;
    }

    private CompositionOperation determineOperation(Exchange exchange) {
        CompositionOperation operation = exchange.getIn().getHeader(CompositionConstants.OPERATION, CompositionOperation.class);
        if (operation == null) {
            operation = endpoint.getOperation();
        }
        return operation;
    }

    private UUID determineCompositionId(Exchange exchange) {
        UUID compositionId = exchange.getIn().getHeader(CompositionConstants.COMPOSITION_ID, UUID.class);
        if (compositionId == null) {
            compositionId = endpoint.getCompositionId();
        }
        return compositionId;
    }

    private Class<?> determineExpectedType(Exchange exchange) {
        Class<?> expectedType = exchange.getIn().getHeader(CompositionConstants.EXPECTED_TYPE, Class.class);
        if (expectedType == null) {
            expectedType = endpoint.getExpectedType();
        }
        return expectedType;
    }

    private CompositionConverter determineConverter(Exchange exchange) {
        CompositionConverter converter = exchange.getIn().getHeader(CompositionConstants.CONVERTER, CompositionConverter.class);
        if (converter == null) {
            converter = endpoint.getConverter();
        }
        return converter;
    }

    private OpenEhrClient getOpenEhrClient() {
        return endpoint.getOpenEhrClient();
    }
}
