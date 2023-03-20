package org.ehrbase.fhirbridge.config.minio;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import org.ehrbase.fhirbridge.minio.MinioException;
import org.ehrbase.fhirbridge.minio.MinioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "fhir-bridge.minio", name = "url")
@EnableConfigurationProperties(MinioProperties.class)
public class MinioConfiguration {

  private static final Logger LOG = LoggerFactory.getLogger(
      MinioConfiguration.class);

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
