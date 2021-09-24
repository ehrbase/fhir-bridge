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

package org.ehrbase.fhirbridge.minio;

import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.InputStream;
import java.util.UUID;

public class MinioService {

    private final MinioClient minioClient;

    private final String bucketName;

    private final String url;

    public MinioService(MinioClient minioClient, String bucketName, String url) {
        this.minioClient = minioClient;
        this.bucketName = bucketName;
        this.url = url;
    }

    public ObjectWriteResponse uploadObject(InputStream in, String contentType) {
        return uploadObject(UUID.randomUUID().toString(), in, contentType);
    }

    public ObjectWriteResponse uploadObject(String objectName, InputStream in, String contentType) {
        try {
            PutObjectArgs args = PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .stream(in, in.available(), -1)
                    .contentType(contentType)
                    .build();
            return minioClient.putObject(args);
        } catch (Exception e) {
            throw new MinioException("Error occurred while uploading", e);
        }
    }

    public String getObjectUrl(String objectName) {
        return UriComponentsBuilder.fromHttpUrl(url)
                .pathSegment(bucketName)
                .path(objectName)
                .build()
                .toUriString();
    }

    public void removeObject(String objectName) {
        try {
            RemoveObjectArgs args = RemoveObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .build();
            minioClient.removeObject(args);
        } catch (Exception e) {
            throw new MinioException("Error occurred while removing", e);
        }
    }
}
