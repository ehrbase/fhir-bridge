package org.ehrbase.fhirbridge.camel.component.ehr;

import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class AqlComponent extends DefaultComponent {

    private final Logger logger = LoggerFactory.getLogger(AqlComponent.class);

    @Override
    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        AqlEndpoint endpoint = new AqlEndpoint(uri, this);
        setProperties(endpoint, parameters);
        return endpoint;
    }
}
