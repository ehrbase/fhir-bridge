package org.ehrbase.fhirbridge.camel.component.ehr;

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultEndpoint;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriPath;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.fhirbridge.ehr.converter.CompositionConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@UriEndpoint(firstVersion = "1.0.0", scheme = "ehr-composition", title = "EHR Composition Component", syntax = "ehr-composition:name", producerOnly = true)
public class CompositionEndpoint extends DefaultEndpoint {

    private final Logger logger = LoggerFactory.getLogger(CompositionEndpoint.class);

    @UriPath
    private String name;

    @UriParam
    private CompositionConfiguration configuration;

    public CompositionEndpoint(String uri, CompositionComponent component, CompositionConfiguration configuration) {
        super(uri, component);
        this.configuration = configuration;
    }

    @Override
    public Producer createProducer() {
        return new CompositionProducer(this);
    }

    @Override
    public Consumer createConsumer(Processor processor) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public CompositionConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(CompositionConfiguration configuration) {
        this.configuration = configuration;
    }

    public OpenEhrClient getOpenEhrClient() {
        return getConfiguration().getOpenEhrClient();
    }

    public void setOpenEhrClient(OpenEhrClient openEhrClient) {
        getConfiguration().setOpenEhrClient(openEhrClient);
    }

    public CompositionOperation getOperation() {
        return getConfiguration().getOperation();
    }

    public void setOperation(CompositionOperation operation) {
        getConfiguration().setOperation(operation);
    }

    public CompositionConverter getConverter() {
        return getConfiguration().getConverter();
    }

    public void setConverter(CompositionConverter converter) {
        getConfiguration().setConverter(converter);
    }
}
