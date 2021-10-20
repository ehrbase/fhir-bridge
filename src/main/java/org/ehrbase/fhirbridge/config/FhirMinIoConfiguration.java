package org.ehrbase.fhirbridge.config;

import org.ehrbase.fhirbridge.camel.processor.DeleteObjectProcessor;
import org.ehrbase.fhirbridge.camel.processor.DocumentReferenceProcessor;
import org.ehrbase.fhirbridge.camel.route.DocumentRouteBuilder;
import org.ehrbase.fhirbridge.config.minio.MinioConfiguration;
import org.ehrbase.fhirbridge.minio.MinioService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ConditionalOnProperty(name = "fhir-bridge.fhir.endpoints.DocumentReference", havingValue = "true")
@Import(MinioConfiguration.class)
public class FhirMinIoConfiguration {

    @Bean
    public DocumentRouteBuilder documentRouteBuilder() {
        return new DocumentRouteBuilder();
    }

    @Bean(name = DocumentReferenceProcessor.BEAN_ID)
    public DocumentReferenceProcessor documentReferenceProcessor(MinioService minioService) {
        return new DocumentReferenceProcessor(minioService);
    }

    @Bean(name = DeleteObjectProcessor.BEAN_ID)
    public DeleteObjectProcessor deleteObjectProcessor(MinioService minioService) {
        return new DeleteObjectProcessor(minioService);
    }
}
