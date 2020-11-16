package org.ehrbase.fhirbridge.camel.component.ehr.composition;

import org.apache.camel.RuntimeCamelException;
import org.ehrbase.client.openehrclient.OpenEhrClient;

@SuppressWarnings("java:S2157")
public class CompositionConfiguration implements Cloneable {

    private OpenEhrClient openEhrClient;

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
