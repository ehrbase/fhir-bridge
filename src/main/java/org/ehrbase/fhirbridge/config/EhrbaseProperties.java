package org.ehrbase.fhirbridge.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "fhir-bridge.ehrbase")
public class EhrbaseProperties {

    private String baseUrl;

    private final Template template = new Template();

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Template getTemplate() {
        return template;
    }

    public static class Template {

        private String prefix;

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }
    }
}
