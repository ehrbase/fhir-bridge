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

package org.ehrbase.fhirbridge.ihe.xds;

import org.apache.camel.builder.RouteBuilder;
import org.openehealth.ipf.commons.ihe.xds.core.requests.ProvideAndRegisterDocumentSet;

/**
 * @author Renaud Subiger
 * @since 1.6
 */
public class XdsRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // @formatter:off
        errorHandler(defaultErrorHandler().logExhaustedMessageHistory(false));

        from("direct:send-to-cdr")
            .routeId("sendToXdsCdr")
            .setProperty("fhir_resource").body()
            .convertBodyTo(ProvideAndRegisterDocumentSet.class)
            .to("xds-iti41://{{fhir-bridge.xds.hostname}}:{{fhir-bridge.xds.port}}{{fhir-bridge.xds.context-path}}")
            .setBody().exchangeProperty("fhir_resource");
        // @formatter:on
    }
}
