package org.ehrbase.fhirbridge.camel.component.ehr;

import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultProducer;
import org.ehrbase.client.aql.query.Query;
import org.ehrbase.client.openehrclient.defaultrestclient.DefaultRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class EhrProducer extends DefaultProducer {

    private final Logger logger = LoggerFactory.getLogger(EhrProducer.class);

    private final EhrEndpoint endpoint;

    public EhrProducer(EhrEndpoint endpoint) {
        super(endpoint);
        this.endpoint = endpoint;
    }

    @Override
    public void process(Exchange exchange) {
        logger.debug("Start processing exchange: {}", exchange);

        DefaultRestClient restClient = endpoint.createRestClient();

        if (endpoint.getEndpointType() == EhrEndpointType.aql) {
            Query<?> query = exchange.getIn().getBody(Query.class);
            exchange.getMessage().setBody(restClient.aqlEndpoint().execute(query));
        } else if (endpoint.getEndpointType() == EhrEndpointType.composition) {
            exchange.getMessage().setBody(restClient.compositionEndpoint(UUID.randomUUID()).mergeCompositionEntity(exchange.getIn().getBody()));
        }
    }
}
