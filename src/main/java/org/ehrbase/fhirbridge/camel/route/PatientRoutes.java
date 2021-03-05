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
import org.ehrbase.fhirbridge.camel.processor.DefaultExceptionHandler;
import org.ehrbase.fhirbridge.camel.processor.EhrIdLookupProcessor;
import org.ehrbase.fhirbridge.camel.processor.ResourceProfileValidator;
import org.hl7.fhir.r4.model.Patient;
import org.springframework.stereotype.Component;

/**
 * Implementation of {@link RouteBuilder} that provides route definitions for transactions linked to {@link Patient} resource.
 *
 * @since 1.0.0
 */
@Component
public class PatientRoutes extends AbstractRouteBuilder {

    private final IFhirResourceDao<Patient> patientDao;

    private final ResourceProfileValidator requestValidator;

    private final EhrIdLookupProcessor ehrIdLookupProcessor;

    private final DefaultExceptionHandler defaultExceptionHandler;

    public PatientRoutes(IFhirResourceDao<Patient> patientDao,
                         ResourceProfileValidator requestValidator,
                         EhrIdLookupProcessor ehrIdLookupProcessor,
                         DefaultExceptionHandler defaultExceptionHandler) {
        this.patientDao = patientDao;
        this.requestValidator = requestValidator;
        this.ehrIdLookupProcessor = ehrIdLookupProcessor;
        this.defaultExceptionHandler = defaultExceptionHandler;
    }

    @Override
    public void configure() {
        // @formatter:off
        from("patient-create:fhirConsumer?fhirContext=#fhirContext")
            .onCompletion()
                .process("auditCreateResourceProcessor")
            .end()
            .onException(Exception.class)
                .process(defaultExceptionHandler)
            .end()
            .process(requestValidator)
            .bean(patientDao, "create(${body})")
            .setHeader(FhirBridgeConstants.METHOD_OUTCOME, body())
            .setBody(simple("${body.resource}"))
            .process(ehrIdLookupProcessor)
//            .to("ehr-composition:compositionProducer?operation=mergeCompositionEntity")
            .setBody(header(FhirBridgeConstants.METHOD_OUTCOME));

        // Find Patient
        from("patient-find:consumer?fhirContext=#fhirContext&lazyLoadBundles=true")
            .choice()
                .when(isSearchOperation())
                    .to("bean:patientDao?method=search(${body}, ${headers.FhirRequestDetails})")
                    .process("bundleProviderResponseProcessor")
                .otherwise()
                    .to("bean:patientDao?method=read(${body}, ${headers.FhirRequestDetails})");

        // @formatter:on
    }
}
