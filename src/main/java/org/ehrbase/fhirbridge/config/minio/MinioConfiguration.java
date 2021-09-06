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

package org.ehrbase.fhirbridge.config.minio;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import org.ehrbase.fhirbridge.minio.MinioException;
import org.ehrbase.fhirbridge.minio.MinioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * {@link Configuration} for MinIO.
 */
@Configuration
@EnableConfigurationProperties(MinioProperties.class)
public class MinioConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(MinioConfiguration.class);

    private final MinioProperties properties;

    public MinioConfiguration(MinioProperties properties) {
        this.properties = properties;
    }

    @Bean
    public MinioService minioService(MinioClient minioClient) {
        try {
            var found = minioClient.bucketExists(BucketExistsArgs.builder()
                    .bucket(properties.getBucketName())
                    .build());
            if (!found) {
                if (properties.isAutoCreateBucket()) {
                    createBucket(minioClient);
                } else {
                    throw new IllegalStateException("The specified bucket does not exist: " + properties.getBucketName());
                }
            }
        } catch (Exception e) {
            throw new MinioException("Error occurred while checking if the specified bucket exists", e);
        }

        return new MinioService(minioClient, properties.getBucketName(), properties.getUrl());
    }

    @Bean
    public MinioClient minioClient() {
        MinioClient minioClient = MinioClient.builder()
                .endpoint(properties.getUrl())
                .credentials(properties.getAccessKey(), properties.getSecretKey())
                .build();

        minioClient.setTimeout(
                properties.getConnectTimeout().toMillis(),
                properties.getWriteTimeout().toMillis(),
                properties.getReadTimeout().toMillis()
        );

        return minioClient;
    }

    private void createBucket(MinioClient minioClient) {
        try {
            minioClient.makeBucket(MakeBucketArgs.builder()
                    .bucket(properties.getBucketName())
                    .build());
            LOG.info("Bucket '{}' created successfully", properties.getBucketName());
        } catch (Exception e) {
            throw new MinioException("Cannot create the specified bucket", e);
        }
    }
}
