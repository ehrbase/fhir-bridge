package org.ehrbase.fhirbridge.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * {@link ConfigurationProperties ConfigurationProperties} to configure security.
 */
@ConfigurationProperties(prefix = "fhir-bridge.security")
public class SecurityProperties {

    private AuthenticationType authenticationType;

    private final Basic basic = new Basic();

    private final OAuth2 oauth2 = new OAuth2();

    public AuthenticationType getAuthenticationType() {
        return authenticationType;
    }

    public void setAuthenticationType(AuthenticationType authenticationType) {
        this.authenticationType = authenticationType;
    }

    public Basic getBasic() {
        return basic;
    }

    public OAuth2 getOauth2() {
        return oauth2;
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

    public static class OAuth2 {

        /**
         * JSON Web Key URI to use to verify the JWT token.
         */
        private String jwkSetUri;

        /**
         * JSON Web Algorithm used for verifying the digital signatures.
         */
        private String jwsAlgorithm = "RS256";

        public String getJwkSetUri() {
            return jwkSetUri;
        }

        public void setJwkSetUri(String jwkSetUri) {
            this.jwkSetUri = jwkSetUri;
        }

        public String getJwsAlgorithm() {
            return jwsAlgorithm;
        }

        public void setJwsAlgorithm(String jwsAlgorithm) {
            this.jwsAlgorithm = jwsAlgorithm;
        }
    }
}
