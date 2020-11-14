package org.ehrbase.fhirbridge.camel.component.ehr;

import org.apache.camel.RuntimeCamelException;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.fhirbridge.ehr.converter.CompositionConverter;

@SuppressWarnings({"java:S2157", "java:S1452"})
public class CompositionConfiguration implements Cloneable {

    private OpenEhrClient openEhrClient;

    private CompositionOperation operation;

    private CompositionConverter converter;

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

    public CompositionConverter getConverter() {
        return converter;
    }

    public void setConverter(CompositionConverter converter) {
        this.converter = converter;
    }
}
