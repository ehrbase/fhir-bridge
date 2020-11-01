package org.ehrbase.fhirbridge.camel.component.ehr;

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultEndpoint;
import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriPath;
import org.ehrbase.client.openehrclient.OpenEhrClientConfig;
import org.ehrbase.client.openehrclient.defaultrestclient.DefaultRestClient;
import org.ehrbase.client.templateprovider.FileBasedTemplateProvider;
import org.ehrbase.client.templateprovider.TemplateProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;

@UriEndpoint(firstVersion = "0.1.0-SNAPSHOT", scheme = "ehr", title = "EHRbase", syntax = "ehr:name", producerOnly = true)
public class EhrEndpoint extends DefaultEndpoint {

    private final Logger logger = LoggerFactory.getLogger(EhrEndpoint.class);

    @UriPath
    @Metadata(required = "true")
    private String name;

    @UriParam
    @Metadata(required = "true")
    private EhrEndpointType ehrEndpointType;

    public EhrEndpoint(String uri, EhrComponent component) {
        super(uri, component);
    }

    @Override
    public Producer createProducer() {
        return new EhrProducer(this);
    }

    @Override
    public Consumer createConsumer(Processor processor) {
        throw new UnsupportedOperationException("Cannot consume from ehr endpoint");
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    // TODO: Extract DefaultRestClient configuration
    public DefaultRestClient createRestClient() {
        OpenEhrClientConfig config = null;
        try {
            config = new OpenEhrClientConfig(new URI("http://localhost:8080/ehrbase/rest/openehr/v1/"));
        } catch (URISyntaxException e) {
            // TODO: Handle exception
        }
        TemplateProvider templateProvider = new FileBasedTemplateProvider(Path.of("D:/Temp/test"));
        return new DefaultRestClient(config, templateProvider);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EhrEndpointType getEhrEndpointType() {
        return ehrEndpointType;
    }

    public void setEhrEndpointType(EhrEndpointType ehrEndpointType) {
        this.ehrEndpointType = ehrEndpointType;
    }
}
