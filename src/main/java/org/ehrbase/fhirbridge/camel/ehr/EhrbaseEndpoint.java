package org.ehrbase.fhirbridge.camel.ehr;

import org.apache.camel.Component;
import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultEndpoint;

public class EhrbaseEndpoint extends DefaultEndpoint {

    public EhrbaseEndpoint(String endpointUri, Component component) {
        super(endpointUri, component);
    }

    @Override
    public Producer createProducer() {
        return new EhrbaseProducer(this);
    }

    @Override
    public Consumer createConsumer(Processor processor) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
