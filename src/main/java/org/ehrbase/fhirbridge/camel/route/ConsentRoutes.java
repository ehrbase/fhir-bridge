/*
 * Copyright 2020-2021 the original author or authors.
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

package org.ehrbase.fhirbridge.camel.route;

import org.springframework.stereotype.Component;

/**
 * Implementation of {@link org.apache.camel.builder.RouteBuilder RouteBuilder} that defines routes for transactions
 * related to {@link org.hl7.fhir.r4.model.Consent Consent} resource.
 *
 * @since 1.0.0
 */
@Component
public class ConsentRoutes extends AbstractRouteBuilder {

    @Override
    public void configure() throws Exception {
        // @formatter:off
        errorHandler(
            defaultErrorHandler()
                .logStackTrace(false));
//        onException(Exception.class)
//                .process("defaultExceptionHandler");

        // Route: Provide Consent
        from("consent-provide:consumer?fhirContext=#fhirContext")
            .routeId("provide-consent-route")
            .process("fhirProfileValidator")
            .process("provideConsentPersistenceProcessor")
            .to("direct:internal-provide-resource");

        // Route: Find Consent
        from("consent-find:consumer?fhirContext=#fhirContext&lazyLoadBundles=true")
            .routeId("find-consent-route")
            .choice()
                .when(isSearchOperation())
                    .to("bean:consentDao?method=search(${body}, ${headers.FhirRequestDetails})")
                    .process("bundleProviderResponseProcessor")
                .otherwise()
                    .to("bean:consentDao?method=read(${body}, ${headers.FhirRequestDetails})");

        // @formatter:on
    }
}
