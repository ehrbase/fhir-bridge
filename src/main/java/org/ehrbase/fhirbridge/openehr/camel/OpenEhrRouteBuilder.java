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

package org.ehrbase.fhirbridge.openehr.camel;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.util.ObjectHelper;
import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.ehrbase.client.exception.ClientException;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.fhirbridge.camel.CamelConstants;
import org.ehrbase.fhirbridge.camel.processor.OpenEhrClientExceptionHandler;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;

/**
 * @author Renaud Subiger
 * @since 1.6
 */
public class OpenEhrRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // @formatter:off
        errorHandler(defaultErrorHandler().logExhaustedMessageHistory(false));

        from("direct:send-to-cdr")
            .routeId("sendToOpenEhrCdr")
            .process(EhrLookupProcessor.BEAN_ID)
            .doTry()
                .to("bean:fhirResourceConversionService?method=convert(${headers.CamelFhirBridgeProfile}, ${body})")
            .doCatch(ConversionException.class)
                .throwException(UnprocessableEntityException.class, "${exception.message}")
            .end()
            .process(exchange -> {
                if (ObjectHelper.isNotEmpty(exchange.getIn().getHeader(CamelConstants.COMPOSITION_ID))) {
                    String compositionId = exchange.getIn().getHeader(CamelConstants.COMPOSITION_ID, String.class);
                    exchange.getIn().getBody(CompositionEntity.class).setVersionUid(new VersionUid(compositionId));
                }
            })
            .doTry()
                .to("ehr-composition:compositionProducer?operation=mergeCompositionEntity")
            .doCatch(ClientException.class)
                .process(new OpenEhrClientExceptionHandler())
            .end()
            .process(ProvideResourceResponseProcessor.BEAN_ID);
        // @formatter:on
    }
}
