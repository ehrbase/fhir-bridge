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

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.client.openehrclient.OpenEhrClientConfig;
import org.ehrbase.client.openehrclient.defaultrestclient.DefaultRestClient;
import org.ehrbase.fhirbridge.openehr.DefaultTemplateProvider;
import org.ehrbase.fhirbridge.openehr.camel.OpenEhrRouteBuilder;
import org.ehrbase.fhirbridge.security.oauth2.AccessTokenService;
import org.ehrbase.fhirbridge.security.oauth2.TokenAuthenticationInterceptor;
import org.ehrbase.webtemplate.templateprovider.TemplateProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.net.URI;

import static org.ehrbase.fhirbridge.config.openehr.OpenEhrProperties.SecurityType.BASIC;
import static org.ehrbase.fhirbridge.config.openehr.OpenEhrProperties.SecurityType.OAUTH2;

/**
 * {@link Configuration} for openEHR.
 *
 * @author Renaud Subiger
 * @since 1.6
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = "fhir-bridge", name = "mode", havingValue = "openehr", matchIfMissing = true)
@ComponentScan(basePackages = "org.ehrbase.fhirbridge.openehr")
@EnableConfigurationProperties(OpenEhrProperties.class)
public class OpenEhrConfiguration {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @PostConstruct
    public void initialize() {
        log.info("Running FHIR Bridge using openEHR");
    }

    @Bean(name = "openEhrRouteBuilder")
    public OpenEhrRouteBuilder routeBuilder() {
        return new OpenEhrRouteBuilder();
    }

    @Bean
    public OpenEhrClient openEhrClient(OpenEhrClientConfig configuration, TemplateProvider templateProvider,
                                       @Qualifier("openEhrHttpClient") HttpClient httpClient) {
        return new DefaultRestClient(configuration, templateProvider, httpClient);
    }

    @Bean
    public OpenEhrClientConfig configuration(OpenEhrProperties openEhrProperties) {
        log.info("Creating OpenEhrClientConfig with configured url : {}", openEhrProperties.getUrl());
        return new OpenEhrClientConfig(URI.create(openEhrProperties.getUrl()));
    }

    @Bean
    public DefaultTemplateProvider templateProvider(CacheManager cacheManager) {
        return new DefaultTemplateProvider(cacheManager.getCache("templateCache"));
    }

    @Bean
    public OperationalTemplateUploader operationalTemplateInitializer(OpenEhrClient openEhrClient,
                                                                      DefaultTemplateProvider templateProvider) {
        return new OperationalTemplateUploader(openEhrClient, templateProvider);
    }

    @Bean(name = "openEhrHttpClient")
    public HttpClient httpClient(ObjectProvider<AccessTokenService> accessTokenService, OpenEhrProperties properties) {
        var builder = HttpClientBuilder.create();

        var security = properties.getSecurity();
        if (security.getType() == BASIC) {
            var credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY,
                    new UsernamePasswordCredentials(security.getUser().getName(), security.getUser().getPassword()));
            builder.setDefaultCredentialsProvider(credentialsProvider);
        } else if (security.getType() == OAUTH2) {
            builder.addInterceptorFirst(new TokenAuthenticationInterceptor(accessTokenService.getIfAvailable()));
        }

        return builder.build();
    }

    @Bean
    @ConditionalOnProperty(name = "fhir-bridge.openehr.security.type", havingValue = "oauth2")
    public AccessTokenService accessTokenService(OpenEhrProperties properties) {
        var oauth2 = properties.getSecurity().getOauth2();
        return new AccessTokenService(oauth2.getTokenUrl(), oauth2.getClientId(), oauth2.getClientSecret());
    }
}
