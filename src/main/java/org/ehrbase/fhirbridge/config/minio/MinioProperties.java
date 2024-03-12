package org.ehrbase.fhirbridge.config.minio;

import java.time.Duration;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "fhir-bridge.minio", ignoreUnknownFields = false)
public class MinioProperties {

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
