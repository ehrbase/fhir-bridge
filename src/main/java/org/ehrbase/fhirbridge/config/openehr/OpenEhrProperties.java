/*
 * Copyright 2022 the original author or authors.
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

package org.ehrbase.fhirbridge.config.openehr;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * {@link ConfigurationProperties} to configure openEHR.
 *
 * @author Renaud Subiger
 * @since 1.6
 */
@ConfigurationProperties(prefix = "fhir-bridge.openehr")
public class OpenEhrProperties {

    private String url;

    private final Security security = new Security();

    private boolean updateTemplatesOnStartup = false;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Security getSecurity() {
        return security;
    }

    public boolean isUpdateTemplatesOnStartup() {
        return updateTemplatesOnStartup;
    }

    public void setUpdateTemplatesOnStartup(boolean updateTemplatesOnStartup) {
        this.updateTemplatesOnStartup = updateTemplatesOnStartup;
    }

    public static class Security {

        private SecurityType type = SecurityType.NONE;

        public SecurityType getType() {
            return type;
        }

        public void setType(SecurityType type) {
            this.type = type;
        }

        private final User user = new User();

        private final OAuth2 oauth2 = new OAuth2();

        public User getUser() {
            return user;
        }

        public OAuth2 getOauth2() {
            return oauth2;
        }
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

        private String tokenUrl;

        private String clientId;

        private String clientSecret;

        public String getTokenUrl() {
            return tokenUrl;
        }

        public void setTokenUrl(String tokenUrl) {
            this.tokenUrl = tokenUrl;
        }

        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public String getClientSecret() {
            return clientSecret;
        }

        public void setClientSecret(String clientSecret) {
            this.clientSecret = clientSecret;
        }
    }
}
