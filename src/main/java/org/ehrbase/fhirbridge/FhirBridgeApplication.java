/*
 * Copyright 2020-2022 the original author or authors.
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

package org.ehrbase.fhirbridge;

import org.openehealth.ipf.boot.fhir.IpfFhirAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import java.util.Locale;

/**
 * {@link Configuration} for Spring Security.
 *
 * @author Renaud Subiger
 * @since 1.0
 */
@SpringBootApplication(exclude = {
        ManagementWebSecurityAutoConfiguration.class,
        SecurityAutoConfiguration.class
})
@ComponentScan(excludeFilters = {
        @ComponentScan.Filter(type = FilterType.REGEX, pattern = "org.ehrbase.fhirbridge.openehr.*"),
        @ComponentScan.Filter(type = FilterType.REGEX, pattern = "org.ehrbase.fhirbridge.ihe.xds.*")
})
public class FhirBridgeApplication {

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        SpringApplication.run(FhirBridgeApplication.class, args);
    }
}
