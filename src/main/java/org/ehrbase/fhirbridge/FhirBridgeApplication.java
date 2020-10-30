package org.ehrbase.fhirbridge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.elasticsearch.rest.RestClientAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;

@SpringBootApplication(exclude = {
        RestClientAutoConfiguration.class,
        ThymeleafAutoConfiguration.class
})
public class FhirBridgeApplication {

    public static void main(String[] args) {
        SpringApplication.run(FhirBridgeApplication.class, args);
    }
}
