package org.ehrbase.fhirbridge.config;

import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.client.openehrclient.OpenEhrClientConfig;
import org.ehrbase.client.openehrclient.defaultrestclient.DefaultRestClient;
import org.ehrbase.client.templateprovider.TemplateProvider;
import org.ehrbase.fhirbridge.ehr.ResourceTemplateProvider;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
@EnableConfigurationProperties(EhrbaseProperties.class)
@Import(EhrbaseTemplateInitializer.class)
public class EhrbaseConfiguration {

    private final EhrbaseProperties properties;

    public EhrbaseConfiguration(EhrbaseProperties properties) {
        this.properties = properties;
    }

    @Bean
    public OpenEhrClientConfig restClientConfig() throws URISyntaxException {
        return new OpenEhrClientConfig(new URI(properties.getBaseUrl()));
    }

    @Bean
    public TemplateProvider templateProvider() {
        return new ResourceTemplateProvider(properties.getTemplate().getPrefix());
    }

    @Bean
    public OpenEhrClient openEhrClient(OpenEhrClientConfig restClientConfig, TemplateProvider templateProvider) {
        return new DefaultRestClient(restClientConfig, templateProvider);
    }
}
