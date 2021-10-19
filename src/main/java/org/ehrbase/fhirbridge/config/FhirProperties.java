package org.ehrbase.fhirbridge.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "fhir-bridge.fhir")
public class FhirProperties {

    private final Convert convert = new Convert();

    private final TerminologyServer terminologyServer = new TerminologyServer();

    public Convert getConvert() {
        return convert;
    }

    public TerminologyServer getTerminologyServer() {
        return terminologyServer;
    }

    public static class Convert {

        private boolean autoPopulateDisplay = false;

        public boolean isAutoPopulateDisplay() {
            return autoPopulateDisplay;
        }

        public void setAutoPopulateDisplay(boolean autoPopulateDisplay) {
            this.autoPopulateDisplay = autoPopulateDisplay;
        }
    }

    public static class TerminologyServer {

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
