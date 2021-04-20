package org.ehrbase.fhirbridge.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "fhir-bridge.debug")
public class DebugProperties {

    private boolean enabled = false;

    private String mappingOutputDirectory;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getMappingOutputDirectory() {
        return mappingOutputDirectory;
    }

    public void setMappingOutputDirectory(String mappingOutputDirectory) {
        this.mappingOutputDirectory = mappingOutputDirectory;
    }
}
