package org.ehrbase.fhirbridge.camel.component.ehr;

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultEndpoint;
import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@UriEndpoint(firstVersion = "0.1.0-SNAPSHOT", scheme = "ehr-aql", title = "EHRbase", syntax = "ehr-aql:ehrId", producerOnly = true)
public class AqlEndpoint extends DefaultEndpoint {

    private final Logger logger = LoggerFactory.getLogger(AqlEndpoint.class);

    @UriPath
    @Metadata(required = "true")
    private String name;

    public AqlEndpoint(String uri, AqlComponent component) {
        super(uri, component);
    }

    @Override
    public Producer createProducer() {
        return new AqlProducer(this);
    }

    @Override
    public Consumer createConsumer(Processor processor) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
