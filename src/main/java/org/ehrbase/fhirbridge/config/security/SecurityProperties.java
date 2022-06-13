/*
 * Copyright 2021-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ehrbase.fhirbridge.config.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * {@link ConfigurationProperties} to configure Spring Security.
 *
 * @author Renaud Subiger
 * @since 1.2
 */
@ConfigurationProperties(prefix = "fhir-bridge.security")
public class SecurityProperties {

    private SecurityType type = SecurityType.NONE;

    private final User user = new User();

    private final OAuth2 oauth2 = new OAuth2();

    public SecurityType getType() {
        return type;
    }

    public void setType(SecurityType type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public OAuth2 getOauth2() {
        return oauth2;
    }

    public enum SecurityType {

        NONE, BASIC, OAUTH2
    }

    public static class User {

        private String name;

        private String password;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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
