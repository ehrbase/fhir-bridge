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
import org.ehrbase.fhirbridge.camel.CamelConstants;
import org.hl7.fhir.r4.model.Condition;
import org.springframework.stereotype.Component;

/**
 * Implementation of {@link RouteBuilder} that provides route definitions for transactions
 * linked to {@link Condition} resource.
 *
 * @since 1.0.0
 */
@Component
public class ConditionRoutes extends AbstractRouteBuilder {

    @Override
    public void configure() throws Exception {
        // @formatter:off
        super.configure();

        // 'Create Condition' route definition
        from("condition-create:consumer?fhirContext=#fhirContext")
            .onCompletion()
                .process("auditCreateResourceProcessor")
            .end()
            .process("fhirProfileValidator")
            .setHeader(CamelConstants.METHOD_OUTCOME, method("conditionDao", "create(${body}, ${headers.FhirRequestDetails})"))
            .process("ehrIdLookupProcessor")
            .to("bean:fhirResourceConversionService?method=convert(${headers.FhirBridgeProfile}, ${body})")
            .to("ehr-composition:compositionProducer?operation=mergeCompositionEntity")
            .process("resourceResponseProcessor");

        // 'Find Condition' route definition
        from("condition-find:consumer?fhirContext=#fhirContext&lazyLoadBundles=true")
            .choice()
                .when(isSearchOperation())
                    .to("bean:conditionDao?method=search(${body}, ${headers.FhirRequestDetails})")
                    .process("bundleProviderResponseProcessor")
                .otherwise()
                    .to("bean:conditionDao?method=read(${body}, ${headers.FhirRequestDetails})");

        // @formatter:on
    }
}
