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

import org.apache.camel.builder.RouteBuilder;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.springframework.stereotype.Component;

/**
 * Implementation of {@link RouteBuilder} that provides route definitions for transactions
 * linked to {@link DiagnosticReport} resource.
 *
 * @since 1.0.0
 */
@Component
public class DiagnosticReportRoutes extends AbstractRouteBuilder {

    @Override
    public void configure() throws Exception {
        // @formatter:off
        super.configure();

        from("direct:internal-provide-diagnostic-report")
            .routeId("internal-provide-diagnostic-report-route")
            .process("provideDiagnosticReportPersistenceProcessor")
            .to("direct:internal-provide-resource");
        // @formatter:on
    }
}
