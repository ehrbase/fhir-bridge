package org.ehrbase.fhirbridge.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * {@link ConfigurationProperties ConfigurationProperties} to configure SSL.
 */
@ConfigurationProperties(prefix = "fhir-bridge.client.ssl")
public class SslProperties {

    private String keyAlias;

    private String KeyPassword;

    private String keyStore;

    private String keyStorePassword;

    private String keyStoreType;

    private String trustStore;

    private String trustStorePassword;

    private String trustStoreType;

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
}
