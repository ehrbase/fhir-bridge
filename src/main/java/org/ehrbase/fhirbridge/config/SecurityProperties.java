package org.ehrbase.fhirbridge.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * {@link ConfigurationProperties ConfigurationProperties} to configure security.
 */
@ConfigurationProperties(prefix = "fhir-bridge.security")
public class SecurityProperties {

    private AuthenticationType authenticationType;

    private final Basic basic = new Basic();

    public AuthenticationType getAuthenticationType() {
        return authenticationType;
    }

    public void setAuthenticationType(AuthenticationType authenticationType) {
        this.authenticationType = authenticationType;
    }

    public Basic getBasic() {
        return basic;
    }

    public enum AuthenticationType {

        /**
         * No authentication
         */
        NONE,

        /**
         * Basic authentication
         */
        BASIC,

        /**
         * OAuth 2.0
         */
        OAUTH2
    }

    public static class Basic {

        private String username;

        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
