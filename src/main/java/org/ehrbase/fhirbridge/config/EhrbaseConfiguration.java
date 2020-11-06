package org.ehrbase.fhirbridge.config;

import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.client.openehrclient.OpenEhrClientConfig;
import org.ehrbase.client.openehrclient.defaultrestclient.DefaultRestClient;
import org.ehrbase.client.templateprovider.TemplateProvider;
import org.ehrbase.fhirbridge.ehr.ClasspathTemplateProvider;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
@EnableConfigurationProperties(EhrbaseProperties.class)
public class EhrbaseConfiguration {

    private final EhrbaseProperties ehrbaseProperties;

    public EhrbaseConfiguration(EhrbaseProperties ehrbaseProperties) {
        this.ehrbaseProperties = ehrbaseProperties;
    }

    @Bean
    public OpenEhrClientConfig restClientConfig() throws URISyntaxException {
        return new OpenEhrClientConfig(new URI(ehrbaseProperties.getBaseUrl()));
    }

    @Bean
    public TemplateProvider templateProvider() {
        return new ClasspathTemplateProvider();
    }

    @Bean
    public OpenEhrClient restClient(OpenEhrClientConfig restClientConfig, TemplateProvider templateProvider) {
        return new DefaultRestClient(restClientConfig, templateProvider);
    }
}
