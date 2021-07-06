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

import ca.uhn.fhir.jpa.api.dao.DaoRegistry;
import ca.uhn.fhir.jpa.api.dao.IFhirResourceDao;
import ca.uhn.fhir.jpa.searchparam.SearchParameterMap;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.api.RestOperationTypeEnum;
import ca.uhn.fhir.rest.api.server.IBundleProvider;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import org.apache.camel.Exchange;
import org.ehrbase.fhirbridge.camel.CamelConstants;
import org.ehrbase.fhirbridge.core.domain.ResourceComposition;
import org.ehrbase.fhirbridge.core.repository.ResourceCompositionRepository;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.instance.model.api.IIdType;
import org.hl7.fhir.r4.model.Resource;
import org.openehealth.ipf.commons.ihe.fhir.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * {@link org.apache.camel.Processor Processor} that handles FHIR operations using DAOs from HAPI FHIR JPA module.
 *
 * @since 1.2.0
 */
@Component(ResourcePersistenceProcessor.BEAN_ID)
@SuppressWarnings({"java:S6212", "rawtypes", "unchecked"})
public class ResourcePersistenceProcessor implements FhirRequestProcessor {

    public static final String BEAN_ID = "resourcePersistenceProcessor";

    private static final Logger LOG = LoggerFactory.getLogger(ResourcePersistenceProcessor.class);

    private final DaoRegistry daoRegistry;

    private final ResourceCompositionRepository resourceCompositionRepository;

    public ResourcePersistenceProcessor(DaoRegistry daoRegistry, ResourceCompositionRepository resourceCompositionRepository) {
        this.daoRegistry = daoRegistry;
        this.resourceCompositionRepository = resourceCompositionRepository;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        RequestDetails requestDetails = getRequestDetails(exchange);
        IFhirResourceDao resourceDao = getResourceDao(exchange, requestDetails);

        switch (requestDetails.getRestOperationType()) {
            case CREATE:
            case TRANSACTION:
                handleCreateOperation(exchange, requestDetails, resourceDao);
                break;
            case READ:
            case VREAD:
                handleReadOperation(exchange, requestDetails, resourceDao);
                break;
            case UPDATE:
                handleUpdateOperation(exchange, requestDetails, resourceDao);
                break;
            case SEARCH_TYPE:
                handleSearchOperation(exchange, requestDetails, resourceDao);
                break;
            default:
                throw new IllegalArgumentException("Unsupported operation: " + requestDetails.getRestOperationType());
        }
    }

    private void handleCreateOperation(Exchange exchange, RequestDetails requestDetails, IFhirResourceDao resourceDao) {
        LOG.trace("Creating {} resource...", requestDetails.getResourceName());

        Resource resource = exchange.getIn().getBody(Resource.class);
        String ifNoneExist = requestDetails.getConditionalUrl(RestOperationTypeEnum.CREATE);

        MethodOutcome outcome = resourceDao.create(resource, ifNoneExist, requestDetails);
        LOG.debug("Created {}: id={}", resource.getResourceType(), outcome.getId());

        exchange.setProperty(CamelConstants.OUTCOME, outcome);
    }

    private void handleReadOperation(Exchange exchange, RequestDetails requestDetails, IFhirResourceDao resourceDao) {
        LOG.trace("Reading {} resource...", requestDetails.getResourceName());

        IIdType id = exchange.getIn().getBody(IIdType.class);
        IBaseResource resource = resourceDao.read(id, requestDetails);
        exchange.getMessage().setBody(resource);
    }

    private void handleUpdateOperation(Exchange exchange, RequestDetails requestDetails, IFhirResourceDao resourceDao) {
        LOG.trace("Updating {} resource...", requestDetails.getResourceName());

        Resource resource = exchange.getIn().getBody(Resource.class);
        String matchUrl = requestDetails.getConditionalUrl(RestOperationTypeEnum.UPDATE);

        MethodOutcome outcome = resourceDao.update(resource, matchUrl, requestDetails);
        LOG.debug("Updated {}: id={}", resource.getResourceType(), outcome.getId());

        resourceCompositionRepository.findById(outcome.getId().getIdPart())
                .map(ResourceComposition::getCompositionId)
                .ifPresent(compositionId -> exchange.getMessage().setHeader(CamelConstants.COMPOSITION_ID, compositionId));

        exchange.setProperty(CamelConstants.OUTCOME, outcome);
    }

    private void handleSearchOperation(Exchange exchange, RequestDetails requestDetails, IFhirResourceDao resourceDao) {
        LOG.trace("Searching {} resources...", requestDetails.getResourceName());

        SearchParameterMap parameters = exchange.getIn().getBody(SearchParameterMap.class);

        IBundleProvider bundleProvider = resourceDao.search(parameters, requestDetails);

        if (exchange.getIn().getHeaders().containsKey(Constants.FHIR_REQUEST_SIZE_ONLY)) {
            exchange.getMessage().setHeader(Constants.FHIR_REQUEST_SIZE_ONLY, bundleProvider.size());
        } else {
            Integer from = exchange.getIn().getHeader(Constants.FHIR_FROM_INDEX, Integer.class);
            Integer to = exchange.getIn().getHeader(Constants.FHIR_TO_INDEX, Integer.class);
            exchange.getMessage().setBody(bundleProvider.getResources(from, to));
        }
    }

    private IFhirResourceDao getResourceDao(Exchange exchange, RequestDetails requestDetails) {
        String resourceName;
        if (requestDetails.getRestOperationType() == RestOperationTypeEnum.TRANSACTION) {
            Resource resource = exchange.getIn().getBody(Resource.class);
            resourceName = resource.getResourceType().name();
        } else {
            resourceName = requestDetails.getResourceName();
        }

        return daoRegistry.getResourceDao(resourceName);
    }
}
