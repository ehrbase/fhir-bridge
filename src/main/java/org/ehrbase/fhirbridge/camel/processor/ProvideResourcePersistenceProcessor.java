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

package org.ehrbase.fhirbridge.camel.processor;

import ca.uhn.fhir.jpa.api.dao.IFhirResourceDao;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.api.RestOperationTypeEnum;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.ehrbase.fhirbridge.camel.CamelConstants;
import org.ehrbase.fhirbridge.core.repository.ResourceMapRepository;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @since 1.2.0
 */
public class ProvideResourcePersistenceProcessor<T extends IBaseResource> implements Processor {

    private static final Logger LOG = LoggerFactory.getLogger(ProvideResourcePersistenceProcessor.class);

    private final IFhirResourceDao<T> resourceDao;

    private final Class<T> resourceType;

    private final ResourceMapRepository resourceMapRepository;

    public ProvideResourcePersistenceProcessor(IFhirResourceDao<T> resourceDao, Class<T> resourceType,
                                               ResourceMapRepository resourceMapRepository) {
        this.resourceDao = resourceDao;
        this.resourceType = resourceType;
        this.resourceMapRepository = resourceMapRepository;
    }

    /**
     * @see Processor#process(Exchange)
     */
    @Override
    public void process(Exchange exchange) throws Exception {
        LOG.trace("Processing...");

        var resource = exchange.getIn().getBody(resourceType);
        var requestDetails = exchange.getIn().getHeader(CamelConstants.FHIR_REQUEST_DETAILS, RequestDetails.class);

        MethodOutcome outcome;
        if (requestDetails.getRestOperationType() == RestOperationTypeEnum.CREATE) {
            outcome = handleCreateResource(resource, requestDetails);
        } else if (requestDetails.getRestOperationType() == RestOperationTypeEnum.UPDATE) {
            outcome = handleUpdateResource(resource, requestDetails);
            resourceMapRepository.findById(outcome.getId().getIdPart())
                    .ifPresent(resourceMap -> exchange.getMessage().setHeader(CamelConstants.COMPOSITION_VERSION_UID, resourceMap.getCompositionVersionUid()));
        } else {
            throw new UnsupportedOperationException("Only 'Create' and 'Update' operations are supported");
        }

        exchange.getMessage().setHeader(CamelConstants.RESOURCE_ID, outcome.getId().getIdPart());
        exchange.setProperty(CamelConstants.METHOD_OUTCOME, outcome);
    }

    /**
     * Creates resource.
     *
     * @param resource       the resource
     * @param requestDetails the context of the current request
     * @return response back to the client
     */
    private MethodOutcome handleCreateResource(T resource, RequestDetails requestDetails) {
        var conditionalUrl = requestDetails.getConditionalUrl(RestOperationTypeEnum.CREATE);
        MethodOutcome outcome = resourceDao.create(resource, conditionalUrl, requestDetails);
        LOG.debug("Created resource: {}", resource);
        return outcome;
    }

    /**
     * Updates an existing resource.
     *
     * @param resource       the updated resource
     * @param requestDetails the context of the current request
     * @return response back to the client
     */
    private MethodOutcome handleUpdateResource(T resource, RequestDetails requestDetails) {
        var conditionalUrl = requestDetails.getConditionalUrl(RestOperationTypeEnum.UPDATE);
        MethodOutcome outcome = resourceDao.update(resource, conditionalUrl, requestDetails);
        LOG.debug("Updated resource: {}", resource);
        return outcome;
    }
}
