package org.ehrbase.fhirbridge.camel.component.ehr;

import org.apache.camel.RuntimeCamelException;
import org.apache.camel.spi.UriParam;
import org.ehrbase.client.openehrclient.OpenEhrClient;

public class CompositionConfiguration implements Cloneable {

    @UriParam(label = "producer")
    private OpenEhrClient openEhrClient;

    @UriParam(label = "producer", enums = "mergeCompositionEntity,find")
    private CompositionOperation operation;

    public CompositionConfiguration() {
    }

    public CompositionConfiguration(OpenEhrClient openEhrClient) {
        this.openEhrClient = openEhrClient;
    }

    public CompositionConfiguration copy() {
        try {
            return (CompositionConfiguration) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeCamelException(e);
        }
    }

    public OpenEhrClient getOpenEhrClient() {
        return openEhrClient;
    }

    public void setOpenEhrClient(OpenEhrClient openEhrClient) {
        this.openEhrClient = openEhrClient;
    }

    public CompositionOperation getOperation() {
        return operation;
    }

    public void setOperation(CompositionOperation operation) {
        this.operation = operation;
    }
}
