package org.ehrbase.fhirbridge.camel.component.ehr;

import org.apache.camel.RuntimeCamelException;
import org.ehrbase.client.openehrclient.OpenEhrClient;

public class CompositionConfiguration implements Cloneable {

    private OpenEhrClient openEhrClient;

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

}
