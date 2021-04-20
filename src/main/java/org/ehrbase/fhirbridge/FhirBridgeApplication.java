package org.ehrbase.fhirbridge;

import org.openehealth.ipf.commons.ihe.fhir.IpfFhirServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FhirBridgeApplication {

    public static void main(String[] args) {
        SpringApplication.run(FhirBridgeApplication.class, args);
    }

    @Bean
    @Primary
    public IpfFhirServlet modifyExisting(IpfFhirServlet existing) {
        existing.registerInterceptor(new SmartOnFhirAuthzInterceptor());
        return existing;
    }
}
