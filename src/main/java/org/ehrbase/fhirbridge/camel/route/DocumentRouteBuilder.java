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

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.apache.camel.builder.RouteBuilder;
import org.ehrbase.client.exception.ClientException;
import org.ehrbase.fhirbridge.camel.CamelConstants;
import org.ehrbase.fhirbridge.camel.processor.DeleteObjectProcessor;
import org.ehrbase.fhirbridge.camel.processor.DocumentReferenceProcessor;
import org.ehrbase.fhirbridge.camel.processor.EhrLookupProcessor;
import org.ehrbase.fhirbridge.camel.processor.FhirProfileValidator;
import org.ehrbase.fhirbridge.camel.processor.OpenEhrClientExceptionHandler;
import org.ehrbase.fhirbridge.camel.processor.PatientReferenceProcessor;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.springframework.stereotype.Component;

import static org.openehealth.ipf.platform.camel.ihe.fhir.core.FhirCamelValidators.MODEL;
import static org.openehealth.ipf.platform.camel.ihe.fhir.core.FhirCamelValidators.VALIDATION_MODE;
import static org.openehealth.ipf.platform.camel.ihe.fhir.core.FhirCamelValidators.itiRequestValidator;

@Component
public class DocumentRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // @formatter:off
        from("documentreference-create:consumer?fhirContext=#fhirContext")
            .onException(Exception.class)
                .process(DeleteObjectProcessor.BEAN_ID)
            .end()
            .setHeader(VALIDATION_MODE, constant(MODEL))
            .process(FhirProfileValidator.BEAN_ID)
            .process(itiRequestValidator())
            .process(PatientReferenceProcessor.BEAN_ID)
            .process(DocumentReferenceProcessor.BEAN_ID)
            .process(EhrLookupProcessor.BEAN_ID)
            .doTry()
                .to("bean:fhirResourceConversionService?method=convert(${headers.CamelFhirBridgeProfile}, ${body})")
            .doCatch(ConversionException.class)
                .process(DeleteObjectProcessor.BEAN_ID)
                .throwException(UnprocessableEntityException.class, "${exception.message}")
            .end()
            .doTry()
                .to("ehr-composition:compositionProducer?operation=mergeCompositionEntity")
            .doCatch(ClientException.class)
                .process(DeleteObjectProcessor.BEAN_ID)
                .process(new OpenEhrClientExceptionHandler())
            .end()
            .setBody(exchangeProperty(CamelConstants.OUTCOME));
        // @formatter:on

//        from("mllp://0.0.0.0:8889?hl7TransactionConfig=#transactionConfiguration")
//                .process(exchange -> {
//                    MDM_T02 message = exchange.getIn().getBody(MDM_T02.class);
//                    EVN evn = message.getEVN();
//                    message.getOBXNTE();
//                });
    }
}
