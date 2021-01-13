package org.ehrbase.fhirbridge.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * {@link ConfigurationProperties ConfigurationProperties} to configure Apache HTTP Client.
 */
@ConfigurationProperties(prefix = "fhir-bridge.http-client")
public class HttpClientProperties {

    private final Ssl ssl = new Ssl();

    public Ssl getSsl() {
        return ssl;
    }

    public static class Ssl {

        private boolean enabled = false;

        private String keyPassword;

        private String keyStore;

        private String keyStorePassword;

        private String keyStoreType;

        private String trustStore;

        private String trustStorePassword;

        private String trustStoreType;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String getKeyPassword() {
            return keyPassword;
        }

        public void setKeyPassword(String keyPassword) {
            this.keyPassword = keyPassword;
        }

        public String getKeyStore() {
            return keyStore;
        }

        public void setKeyStore(String keyStore) {
            this.keyStore = keyStore;
        }

        public String getKeyStorePassword() {
            return keyStorePassword;
        }

        public void setKeyStorePassword(String keyStorePassword) {
            this.keyStorePassword = keyStorePassword;
        }

        public String getKeyStoreType() {
            return keyStoreType;
        }

        public void setKeyStoreType(String keyStoreType) {
            this.keyStoreType = keyStoreType;
        }

        public String getTrustStore() {
            return trustStore;
        }

        public void setTrustStore(String trustStore) {
            this.trustStore = trustStore;
        }

        public String getTrustStorePassword() {
            return trustStorePassword;
        }

        public void setTrustStorePassword(String trustStorePassword) {
            this.trustStorePassword = trustStorePassword;
        }

        public String getTrustStoreType() {
            return trustStoreType;
        }

        public void setTrustStoreType(String trustStoreType) {
            this.trustStoreType = trustStoreType;
        }
    }
}
