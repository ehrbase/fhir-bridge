package org.ehrbase.fhirbridge;

import org.openehealth.ipf.commons.ihe.fhir.IpfFhirServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientAutoConfiguration;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@SpringBootApplication(exclude = {
        QuartzAutoConfiguration.class,
        ElasticsearchRestClientAutoConfiguration.class,
        ThymeleafAutoConfiguration.class})
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

