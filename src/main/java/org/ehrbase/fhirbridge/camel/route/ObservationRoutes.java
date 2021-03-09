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

import ca.uhn.fhir.jpa.api.dao.IFhirResourceDao;
import org.apache.camel.builder.RouteBuilder;
import org.ehrbase.fhirbridge.camel.FhirBridgeConstants;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConstants;
import org.ehrbase.fhirbridge.camel.processor.DefaultExceptionHandler;
import org.ehrbase.fhirbridge.camel.processor.EhrIdLookupProcessor;
import org.ehrbase.fhirbridge.camel.processor.ResourceProfileValidator;
import org.ehrbase.fhirbridge.camel.processor.ResourceResponseProcessor;
import org.ehrbase.fhirbridge.ehr.converter.CompositionConverterResolver;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.stereotype.Component;

/**
 * Implementation of {@link RouteBuilder} that provides route definitions for transactions
 * linked to {@link Observation} resource.
 *
 * @since 1.0.0
 */
@Component
public class ObservationRoutes extends AbstractRouteBuilder {

    private final IFhirResourceDao<Observation> observationDao;

    private final EhrIdLookupProcessor ehrIdLookupProcessor;

    private final ResourceResponseProcessor resourceResponseProcessor;

    private final ResourceProfileValidator requestValidator;

    private final CompositionConverterResolver compositionConverterResolver;

    private final DefaultExceptionHandler defaultExceptionHandler;

    public ObservationRoutes(IFhirResourceDao<Observation> observationDao,
                             EhrIdLookupProcessor ehrIdLookupProcessor,
                             ResourceResponseProcessor resourceResponseProcessor,
                             ResourceProfileValidator requestValidator,
                             CompositionConverterResolver compositionConverterResolver,
                             DefaultExceptionHandler defaultExceptionHandler) {
        this.observationDao = observationDao;
        this.ehrIdLookupProcessor = ehrIdLookupProcessor;
        this.resourceResponseProcessor = resourceResponseProcessor;
        this.requestValidator = requestValidator;
        this.compositionConverterResolver = compositionConverterResolver;
        this.defaultExceptionHandler = defaultExceptionHandler;
    }

    @Override
    public void configure() {
        // @formatter:off
        onException(Exception.class)
                .process(defaultExceptionHandler);

        // 'Create Observation' route definition

        from("observation-create:consumer?fhirContext=#fhirContext")
            .onCompletion()
                .process("auditCreateResourceProcessor")
            .end()
            .process(requestValidator)
            .to("direct:process-observation");

        // 'Find Observation' route definition

        from("observation-find:consumer?fhirContext=#fhirContext&lazyLoadBundles=true")
            .choice()
                .when(isSearchOperation())
                    .to("bean:observationDao?method=search(${body}, ${headers.FhirRequestDetails})")
                    .process("bundleProviderResponseProcessor")
                .otherwise()
                    .to("bean:observationDao?method=read(${body}, ${headers.FhirRequestDetails})");

        // Internal routes definition

        from("direct:process-observation")
            .setHeader(FhirBridgeConstants.METHOD_OUTCOME, method(observationDao, "create(${body}, ${headers.FhirRequestDetails})"))
            .process(ehrIdLookupProcessor)
            .setHeader(CompositionConstants.COMPOSITION_CONVERTER, method(compositionConverterResolver, "resolve(${header.FhirBridgeProfile})"))
            .to("ehr-composition:compositionEndpoint?operation=mergeCompositionEntity")
            .process(resourceResponseProcessor);

        // @formatter:on
    }
}
