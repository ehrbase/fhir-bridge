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
import org.ehrbase.fhirbridge.camel.component.ehr.aql.AqlConstants;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConstants;
import org.ehrbase.fhirbridge.camel.processor.DefaultExceptionHandler;
import org.ehrbase.fhirbridge.camel.processor.EhrIdLookupProcessor;
import org.ehrbase.fhirbridge.camel.processor.ResourceProfileValidator;
import org.ehrbase.fhirbridge.ehr.converter.CompositionConverterResolver;
import org.ehrbase.fhirbridge.camel.processor.ResourceResponseProcessor;
import org.ehrbase.fhirbridge.ehr.converter.ProcedureCompositionConverter;
import org.hl7.fhir.r4.model.Procedure;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Implementation of {@link RouteBuilder} that provides route definitions for transactions
 * linked to {@link Procedure} resource.
 *
 * @since 1.0.0
 */
@Component
public class ProcedureRoutes extends AbstractRouteBuilder {

    private final IFhirResourceDao<Procedure> procedureDao;

    private final ResourceProfileValidator requestValidator;

    private final CompositionConverterResolver compositionConverterResolver;

    private final EhrIdLookupProcessor ehrIdLookupProcessor;

    private final DefaultExceptionHandler defaultExceptionHandler;

    public ProcedureRoutes(IFhirResourceDao<Procedure> procedureDao,
                           ResourceProfileValidator requestValidator,
                           EhrIdLookupProcessor ehrIdLookupProcessor,
                           CompositionConverterResolver compositionConverterResolver,
                           DefaultExceptionHandler defaultExceptionHandler) {
        this.procedureDao = procedureDao;
        this.requestValidator = requestValidator;
        this.ehrIdLookupProcessor = ehrIdLookupProcessor;
        this.defaultExceptionHandler = defaultExceptionHandler;
        this.compositionConverterResolver = compositionConverterResolver;
    }

    @Override
    public void configure() {
        // @formatter:off
        // 'Create Procedure' route definition

        from("procedure-create:consumer?fhirContext=#fhirContext")
            .onCompletion()
                .process("auditCreateResourceProcessor")
                .end()
                .onException(Exception.class)
                .process(defaultExceptionHandler)
                .end()
                .process(requestValidator)
                .bean(procedureDao, "create(${body})")
                .setHeader(FhirBridgeConstants.METHOD_OUTCOME, body())
                .setBody(simple("${body.resource}"))
                .process(ehrIdLookupProcessor)
                .setHeader(CompositionConstants.COMPOSITION_CONVERTER, method(compositionConverterResolver, "resolve(${header.FhirBridgeProfile})"))
                .to("ehr-composition:compositionProducer?operation=mergeCompositionEntity")
                .process(new ResourceResponseProcessor());

        // 'Find Procedure' route definition

        from("procedure-find:consumer?fhirContext=#fhirContext&lazyLoadBundles=true")
            .choice()
                .when(isSearchOperation())
                    .to("bean:procedureDao?method=search(${body}, ${headers.FhirRequestDetails})")
                    .process("bundleProviderResponseProcessor")
                .otherwise()
                    .to("bean:procedureDao?method=read(${body}, ${headers.FhirRequestDetails})");

        // @formatter:on
    }

    // TODO: Update when Apache Camel > 3.x
    @Bean
    public ProcedureCompositionConverter procedureCompositionConverter() {
        return new ProcedureCompositionConverter();
    }
}
