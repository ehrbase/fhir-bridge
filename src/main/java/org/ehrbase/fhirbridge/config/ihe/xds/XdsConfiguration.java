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

package org.ehrbase.fhirbridge.config.ihe.xds;

import org.ehrbase.fhirbridge.ihe.xds.XdsRouteBuilder;
import org.ehrbase.fhirbridge.ihe.xds.converter.ProvideAndRegisterDocumentSetConverter;
import org.openehealth.ipf.commons.ihe.ws.cxf.payload.OutPayloadLoggerInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * {@link Configuration} for IHE XDS.
 *
 * @author Renaud Subiger
 * @since 1.6
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = "fhir-bridge", name = "mode", havingValue = "xds")
@EnableConfigurationProperties(XdsProperties.class)
public class XdsConfiguration {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @PostConstruct
    public void initialize() {
        log.info("Running FHIR Bridge using XDS");
    }

    @Bean(name = "xdsRouteBuilder")
    public XdsRouteBuilder routeBuilder() {
        return new XdsRouteBuilder();
    }

    @Bean
    public ProvideAndRegisterDocumentSetConverter iti41Converter() {
        return new ProvideAndRegisterDocumentSetConverter();
    }

    @Bean(name = "serverOutLogger")
    public OutPayloadLoggerInterceptor serverOutLogger() {
        return new OutPayloadLoggerInterceptor("logs/soap_message.txt");
    }


}
