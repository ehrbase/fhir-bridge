package org.ehrbase.fhirbridge.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class FhirConfiguration {

//    private final IpfFhirConfigurationProperties config;
//
//    public FhirConfiguration(IpfFhirConfigurationProperties config) {
//        this.config = config;
//    }
//
//    @Bean
//    public IpfFhirServlet fhirServlet(FhirContext fhirContext, IServerConformanceProvider<CapabilityStatement> serverConformanceProvider, ObjectProvider<IPagingProvider> pagingProvider, IServerAddressStrategy serverAddressStrategy, INarrativeGenerator narrativeGenerator) {
//        IpfFhirServlet fhirServlet = new IpfBootFhirServlet(fhirContext, pagingProvider);
//        IpfFhirConfigurationProperties.Servlet servletProperties = this.config.getServlet();
//        fhirServlet.setLogging(servletProperties.isLogging());
//        fhirServlet.setPrettyPrint(servletProperties.isPrettyPrint());
//        fhirServlet.setResponseHighlighting(servletProperties.isResponseHighlighting());
//        fhirServlet.setStrictErrorHandler(servletProperties.isStrict());
//        fhirServlet.setServerConformanceProvider(serverConformanceProvider);
//        fhirServlet.setServerAddressStrategy(serverAddressStrategy);
//        fhirServlet.setPagingProviderSize(servletProperties.getPagingRequests());
//        fhirServlet.setMaximumPageSize(servletProperties.getMaxPageSize());
//        fhirServlet.setDefaultPageSize(servletProperties.getDefaultPageSize());
//        if (narrativeGenerator != null) {
//            fhirServlet.setNarrativeGenerator(narrativeGenerator);
//        }
//
//        fhirServlet.registerInterceptor(new SimpleInterceptor());
//
//        return fhirServlet;
//    }
}
