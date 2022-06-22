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

import java.time.Duration;

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

    /**
     * Configuration properties for MinIO.
     */
    @ConfigurationProperties(prefix = "fhir-bridge.minio", ignoreUnknownFields = false)
    public static class MinioProperties {

        /**
         * MinIO instance URL.
         */
        private String url;

        /**
         * Credentials access key.
         */
        private String accessKey;

        /**
         * Credentials secret key.
         */
        private String secretKey;

        /**
         * Bucket name.
         */
        private String bucketName;

        /**
         * Whether to create the bucket automatically if it does not exist.
         */
        private boolean autoCreateBucket = true;

        /**
         * HTTP connect timeout in seconds.
         */
        private Duration connectTimeout = Duration.ofSeconds(60);

        /**
         * HTTP write timeout in seconds.
         */
        private Duration writeTimeout = Duration.ofSeconds(60);

        /**
         * HTTP read timeout in seconds.
         */
        private Duration readTimeout = Duration.ofSeconds(60);

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAccessKey() {
            return accessKey;
        }

        public void setAccessKey(String accessKey) {
            this.accessKey = accessKey;
        }

        public String getSecretKey() {
            return secretKey;
        }

        public void setSecretKey(String secretKey) {
            this.secretKey = secretKey;
        }

        public String getBucketName() {
            return bucketName;
        }

        public void setBucketName(String bucketName) {
            this.bucketName = bucketName;
        }

        public boolean isAutoCreateBucket() {
            return autoCreateBucket;
        }

        public void setAutoCreateBucket(boolean autoCreateBucket) {
            this.autoCreateBucket = autoCreateBucket;
        }

        public Duration getConnectTimeout() {
            return connectTimeout;
        }

        public void setConnectTimeout(Duration connectTimeout) {
            this.connectTimeout = connectTimeout;
        }

        public Duration getWriteTimeout() {
            return writeTimeout;
        }

        public void setWriteTimeout(Duration writeTimeout) {
            this.writeTimeout = writeTimeout;
        }

        public Duration getReadTimeout() {
            return readTimeout;
        }

        public void setReadTimeout(Duration readTimeout) {
            this.readTimeout = readTimeout;
        }
    }
}
