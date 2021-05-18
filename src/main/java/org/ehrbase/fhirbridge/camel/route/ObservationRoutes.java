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
import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.fhirbridge.camel.CamelConstants;
import org.ehrbase.fhirbridge.camel.processor.ProvideResourcePersistenceProcessor;
import org.ehrbase.fhirbridge.core.repository.ResourceMapRepository;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Implementation of {@link RouteBuilder} that provides route definitions for transactions
 * linked to {@link Observation} resource.
 *
 * @since 1.0.0
 */
@Component
public class ObservationRoutes extends AbstractRouteBuilder {

    @Override
    public void configure() throws Exception {
        // @formatter:off
        super.configure();

        // 'Provide Observation' route definition
        from("observation-provide:consumer?fhirContext=#fhirContext")
//            .onCompletion()
//                .process("auditEventProcessor")
//            .end()
            .process("fhirProfileValidator")
            .process("observationPersistenceProcessor")
            .process("ehrIdLookupProcessor")
            .to("bean:fhirResourceConversionService?method=convert(${headers.FhirBridgeProfile}, ${body})")
            .choice()
                .when(header(CamelConstants.COMPOSITION_VERSION_UID).isNotNull())
                    .process(exchange -> {
                        CompositionEntity composition = exchange.getIn().getMandatoryBody(CompositionEntity.class);
                        composition.setVersionUid(new VersionUid(exchange.getIn().getHeader(CamelConstants.COMPOSITION_VERSION_UID, String.class)));
                    })
                .end()
            .to("ehr-composition:producer?operation=mergeCompositionEntity")
            .process("provideResourceResponseProcessor");

        // 'Create Observation' route definition
        from("observation-create:consumer?fhirContext=#fhirContext")
            .onCompletion()
                .process("auditCreateResourceProcessor")
            .end()
            .process("fhirProfileValidator")
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
            .setHeader(CamelConstants.METHOD_OUTCOME, method("observationDao", "create(${body}, ${headers.FhirRequestDetails})"))
            .process("ehrIdLookupProcessor")
            .to("bean:fhirResourceConversionService?method=convert(${headers.FhirBridgeProfile}, ${body})")
            .to("ehr-composition:compositionEndpoint?operation=mergeCompositionEntity")
            .process("resourceResponseProcessor");

        // @formatter:on
    }

    @Bean
    public ProvideResourcePersistenceProcessor<Observation> observationPersistenceProcessor(IFhirResourceDao<Observation> observationDao,
                                                                                            ResourceMapRepository resourceMapRepository) {
        return new ProvideResourcePersistenceProcessor<>(observationDao, Observation.class, resourceMapRepository);
    }
}
