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
import org.ehrbase.fhirbridge.camel.processor.EhrIdLookupProcessor;
import org.ehrbase.fhirbridge.camel.processor.ResourceResponseProcessor;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.springframework.stereotype.Component;

/**
 * Implementation of {@link RouteBuilder} that provides route definitions for transactions
 * linked to {@link QuestionnaireResponse} resource.
 *
 * @since 1.0.0
 */
@Component
public class QuestionnaireResponseRoutes extends AbstractRouteBuilder {

    private final IFhirResourceDao<QuestionnaireResponse> questionnaireResponseDao;

    private final EhrIdLookupProcessor ehrIdLookupProcessor;

    private final ResourceResponseProcessor resourceResponseProcessor;

    public QuestionnaireResponseRoutes(IFhirResourceDao<QuestionnaireResponse> questionnaireResponseDao,
                                       EhrIdLookupProcessor ehrIdLookupProcessor,
                                       ResourceResponseProcessor resourceResponseProcessor) {
        this.questionnaireResponseDao = questionnaireResponseDao;
        this.ehrIdLookupProcessor = ehrIdLookupProcessor;
        this.resourceResponseProcessor = resourceResponseProcessor;
    }

    @Override
    public void configure() {
        // @formatter:off

        // 'Create Questionnaire-Response' route definition

        from("questionnaire-response-create:consumer?fhirContext=#fhirContext")
            .onCompletion()
                .process("auditCreateResourceProcessor")
            .end()
            .onException(Exception.class)
                .process("defaultExceptionHandler")
            .end()
            .setHeader(FhirBridgeConstants.METHOD_OUTCOME, method(questionnaireResponseDao, "create"))
            .process(ehrIdLookupProcessor)
            .to("bean:fhirResourceConversionService?method=convert(${headers.FhirBridgeProfile}, ${body})")
            .to("ehr-composition:compositionProducer?operation=mergeCompositionEntity")
            .process(resourceResponseProcessor);

        // 'Find Questionnaire-Response' route definition

        from("questionnaire-response-find:consumer?fhirContext=#fhirContext&lazyLoadBundles=true")
            .choice()
                .when(isSearchOperation())
                    .to("bean:questionnaireResponseDao?method=search(${body}, ${headers.FhirRequestDetails})")
                    .process("bundleProviderResponseProcessor")
                .otherwise()
                    .to("bean:questionnaireResponseDao?method=read(${body}, ${headers.FhirRequestDetails})");

        // @formatter:on
    }
}
