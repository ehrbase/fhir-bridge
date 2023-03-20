/*
 * Copyright 2020-2021 the original author or authors.
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

package org.ehrbase.fhirbridge.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * {@link ConfigurationProperties ConfigurationProperties} to configure HAPI FHIR JPA Server.
 *
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "fhir-bridge.fhir.jpa")
public class HapiFhirJpaProperties {

    private boolean allowExternalReferences = true;

    private boolean allowInlineMatchUrlReferences = true;

    private boolean autoCreatePlaceholderReferences = true;

    private boolean populateIdentifierInAutoCreatedPlaceholderReferences = true;

    public boolean isAllowExternalReferences() {
        return allowExternalReferences;
    }

    public void setAllowExternalReferences(boolean allowExternalReferences) {
        this.allowExternalReferences = allowExternalReferences;
    }

    public boolean isAllowInlineMatchUrlReferences() {
        return allowInlineMatchUrlReferences;
    }

    public void setAllowInlineMatchUrlReferences(boolean allowInlineMatchUrlReferences) {
        this.allowInlineMatchUrlReferences = allowInlineMatchUrlReferences;
    }

    public boolean isAutoCreatePlaceholderReferences() {
        return autoCreatePlaceholderReferences;
    }

    public void setAutoCreatePlaceholderReferences(boolean autoCreatePlaceholderReferences) {
        this.autoCreatePlaceholderReferences = autoCreatePlaceholderReferences;
    }

    public boolean isPopulateIdentifierInAutoCreatedPlaceholderReferences() {
        return populateIdentifierInAutoCreatedPlaceholderReferences;
    }

    public void setPopulateIdentifierInAutoCreatedPlaceholderReferences(boolean populateIdentifierInAutoCreatedPlaceholderReferences) {
        this.populateIdentifierInAutoCreatedPlaceholderReferences = populateIdentifierInAutoCreatedPlaceholderReferences;
    }
}
