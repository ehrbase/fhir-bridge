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

package org.ehrbase.fhirbridge.fhir.composition;

import ca.uhn.fhir.rest.annotation.ConditionalUrlParam;
import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.annotation.Update;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import org.hl7.fhir.r4.model.Composition;
import org.hl7.fhir.r4.model.IdType;
import org.openehealth.ipf.commons.ihe.fhir.AbstractPlainProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Implementation of {@link AbstractPlainProvider} that handles 'Provide Composition' transaction
 * using {@link Create} and {@link Update} operations.
 *
 * @since 1.2.0
 */
@SuppressWarnings("unused")
public class ProvideCompositionProvider extends AbstractPlainProvider {

    private static final Logger LOG = LoggerFactory.getLogger(ProvideCompositionProvider.class);

    @Create
    public MethodOutcome create(@ResourceParam Composition composition,
                                RequestDetails requestDetails,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        LOG.trace("Executing 'Provide Composition' transaction using 'create' operation...");
        return requestAction(composition, null, request, response, requestDetails);
    }

    @Update
    public MethodOutcome update(@ResourceParam Composition composition,
                                @IdParam IdType id,
                                @ConditionalUrlParam String conditionalUrl,
                                RequestDetails requestDetails,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        LOG.trace("Executing 'Provide Composition' transaction using 'update' operation...");
        return requestAction(composition, null, request, response, requestDetails);
    }
}
