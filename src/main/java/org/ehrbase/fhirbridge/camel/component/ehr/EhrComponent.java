package org.ehrbase.fhirbridge.camel.component.ehr;

import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

//@org.apache.camel.spi.annotations.Component("ehr")
public class EhrComponent extends DefaultComponent {

    private final Logger logger = LoggerFactory.getLogger(EhrComponent.class);

    @Override
    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        Endpoint endpoint = new EhrEndpoint(uri, this);
        setProperties(endpoint, parameters);
        return endpoint;
    }
}
