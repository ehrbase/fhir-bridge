package org.ehrbase.fhirbridge.camel.ehr;

import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;

import java.util.Map;

public class EhrbaseComponent extends DefaultComponent {

    @Override
    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) {
        return new EhrbaseEndpoint(uri, this);
    }
}
