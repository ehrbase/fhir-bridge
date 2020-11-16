package org.ehrbase.fhirbridge.camel.component.ehr.aql;

import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultProducer;
import org.ehrbase.client.aql.parameter.ParameterValue;
import org.ehrbase.client.aql.query.Query;

import javax.persistence.NonUniqueResultException;
import java.util.List;

public class AqlProducer extends DefaultProducer {

    private final AqlEndpoint endpoint;

    public AqlProducer(AqlEndpoint endpoint) {
        super(endpoint);
        this.endpoint = endpoint;
    }

    @Override
    public void process(Exchange exchange) {
        Query<?> query = exchange.getIn().getHeader(AqlConstants.QUERY, Query.class);
        if (query == null) {
            throw new IllegalArgumentException("");
        }

        ParameterValue<?>[] parameters = exchange.getIn().getBody(ParameterValue[].class);
        List<?> result = endpoint.getOpenEhrClient().aqlEndpoint().execute(query, parameters);

        if (endpoint.isSingleResult()) {
            if (result.size() > 1) {
                throw new NonUniqueResultException();
            }
            exchange.getMessage().setBody(result.get(0));
        } else {
            exchange.getMessage().setBody(result);
        }
    }
}
