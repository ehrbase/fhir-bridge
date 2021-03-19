package org.ehrbase.fhirbridge.config;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.narrative.INarrativeGenerator;
import ca.uhn.fhir.rest.server.IPagingProvider;
import ca.uhn.fhir.rest.server.IServerAddressStrategy;
import ca.uhn.fhir.rest.server.IServerConformanceProvider;
import ca.uhn.fhir.rest.server.interceptor.CorsInterceptor;
import ca.uhn.fhir.rest.server.interceptor.RequestValidatingInterceptor;
import org.hl7.fhir.r4.model.CapabilityStatement;
import org.openehealth.ipf.boot.fhir.IpfBootFhirServlet;
import org.openehealth.ipf.boot.fhir.IpfFhirConfigurationProperties;
import org.openehealth.ipf.commons.ihe.fhir.IpfFhirServlet;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;

/**
 * {@link Configuration Configuration} for IPF FHIR Servlet.
 */
@Configuration
public class IpfFhirConfiguration {

    @Bean
    @SuppressWarnings("java:S107")
    public IpfFhirServlet fhirServlet(IpfFhirConfigurationProperties properties,
                                      FhirContext fhirContext,
                                      IServerConformanceProvider<CapabilityStatement> serverConformanceProvider,
                                      ObjectProvider<IPagingProvider> pagingProvider,
                                      IServerAddressStrategy serverAddressStrategy,
                                      INarrativeGenerator narrativeGenerator,
                                      RequestValidatingInterceptor requestValidatingInterceptor,
                                      CorsInterceptor corsInterceptor) {

        IpfFhirServlet fhirServlet = new IpfBootFhirServlet(fhirContext, pagingProvider);
        IpfFhirConfigurationProperties.Servlet servletProperties = properties.getServlet();
        fhirServlet.setLogging(servletProperties.isLogging());
        fhirServlet.setPrettyPrint(servletProperties.isPrettyPrint());
        fhirServlet.setResponseHighlighting(servletProperties.isResponseHighlighting());
        fhirServlet.setStrictErrorHandler(servletProperties.isStrict());
        fhirServlet.setServerConformanceProvider(serverConformanceProvider);
        fhirServlet.setServerAddressStrategy(serverAddressStrategy);
        fhirServlet.setPagingProviderSize(servletProperties.getPagingRequests());
        fhirServlet.setMaximumPageSize(servletProperties.getMaxPageSize());
        fhirServlet.setDefaultPageSize(servletProperties.getDefaultPageSize());
        if (narrativeGenerator != null) {
            fhirServlet.setNarrativeGenerator(narrativeGenerator);
        }

        // Register interceptors
        fhirServlet.registerInterceptor(requestValidatingInterceptor);
        fhirServlet.registerInterceptor(corsInterceptor);

        return fhirServlet;
    }

    @Bean
    public CorsInterceptor corsInterceptor() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedHeader("*");
        configuration.addAllowedOrigin("*");
        configuration.addAllowedMethod("*");
        CorsInterceptor interceptor = new CorsInterceptor(configuration);
        interceptor.setConfig(configuration);
        return interceptor;
    }
}
