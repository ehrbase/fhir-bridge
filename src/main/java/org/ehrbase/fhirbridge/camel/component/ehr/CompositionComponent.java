package org.ehrbase.fhirbridge.camel.component.ehr;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;

/**
 * EHR Composition component which uses EHRbase SDK.
 */
public class CompositionComponent extends DefaultComponent {

    private final Logger logger = LoggerFactory.getLogger(CompositionComponent.class);

    private CompositionConfiguration configuration;

    private boolean allowAutoWiredOpenEhrClient = true;

    public CompositionComponent() {
        this.configuration = createConfiguration();
    }

    public CompositionComponent(CamelContext context) {
        super(context);
        this.configuration = createConfiguration();
    }

    @Override
    protected void doStart() throws Exception {
        if (configuration.getOpenEhrClient() == null && isAllowAutoWiredOpenEhrClient()) {
            Set<OpenEhrClient> beans = getCamelContext().getRegistry().findByType(OpenEhrClient.class);
            if (beans.size() == 1) {
                OpenEhrClient client = beans.iterator().next();
                configuration.setOpenEhrClient(client);
            } else if (beans.size() > 1) {
                logger.debug("Cannot autowire OpenEhrClient as {} instances found in registry.", beans.size());
            }
        }
        super.doStart();
    }

    @Override
    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        final CompositionConfiguration newConfiguration = getConfiguration().copy();

        CompositionEndpoint endpoint = new CompositionEndpoint(uri, this, newConfiguration);
        setProperties(endpoint, parameters);
        return endpoint;
    }

    protected CompositionConfiguration createConfiguration() {
        return new CompositionConfiguration();
    }

    public CompositionConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(CompositionConfiguration configuration) {
        this.configuration = configuration;
    }

    public boolean isAllowAutoWiredOpenEhrClient() {
        return allowAutoWiredOpenEhrClient;
    }

    public void setAllowAutoWiredOpenEhrClient(boolean allowAutoWiredOpenEhrClient) {
        this.allowAutoWiredOpenEhrClient = allowAutoWiredOpenEhrClient;
    }

    public OpenEhrClient getOpenEhrClient() {
        return configuration.getOpenEhrClient();
    }
}
