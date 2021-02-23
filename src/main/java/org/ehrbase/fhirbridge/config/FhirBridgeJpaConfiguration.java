package org.ehrbase.fhirbridge.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "org.ehrbase.fhirbridge.fhir.support")
public class FhirBridgeJpaConfiguration {

}
