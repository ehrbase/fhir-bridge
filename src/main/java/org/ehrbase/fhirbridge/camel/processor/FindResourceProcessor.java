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
import ca.uhn.fhir.jpa.searchparam.SearchParameterMap;
import ca.uhn.fhir.rest.api.RestOperationTypeEnum;
import ca.uhn.fhir.rest.api.server.IBundleProvider;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import org.apache.camel.Exchange;
import org.apache.camel.InvalidPayloadException;
import org.apache.camel.Processor;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.instance.model.api.IIdType;
import org.openehealth.ipf.commons.ihe.fhir.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Generic Processor that implements 'Find [resource type]' transaction using the provided {@link IFhirResourceDao}.
 *
 * @param <T> the type of the FHIR resource
 * @since 1.2.0
 */
public class FindResourceProcessor<T extends IBaseResource> implements Processor {

    private static final Logger LOG = LoggerFactory.getLogger(FindResourceProcessor.class);

    private final IFhirResourceDao<T> resourceDao;

    public FindResourceProcessor(IFhirResourceDao<T> resourceDao) {
        this.resourceDao = resourceDao;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        LOG.trace("Processing...");

        if (isSearchOperation(exchange)) {
            handleSearchOperation(exchange);
        } else {
            handleReadOperation(exchange);
        }
    }

    private void handleSearchOperation(Exchange exchange) throws InvalidPayloadException {
        LOG.debug("Execute 'search' operation");

        SearchParameterMap parameters = exchange.getIn().getMandatoryBody(SearchParameterMap.class);

        IBundleProvider bundleProvider = resourceDao.search(parameters, extractRequestDetails(exchange));

        if (exchange.getIn().getHeaders().containsKey(Constants.FHIR_REQUEST_SIZE_ONLY)) {
            exchange.getMessage().setHeader(Constants.FHIR_REQUEST_SIZE_ONLY, bundleProvider.size());
        } else {
            Integer from = exchange.getIn().getHeader(Constants.FHIR_FROM_INDEX, Integer.class);
            Integer to = exchange.getIn().getHeader(Constants.FHIR_TO_INDEX, Integer.class);
            exchange.getMessage().setBody(bundleProvider.getResources(from, to));
        }
    }

    private void handleReadOperation(Exchange exchange) throws InvalidPayloadException {
        LOG.debug("Execute 'read'/'vread' operation");

        IIdType id = exchange.getIn().getMandatoryBody(IIdType.class);
        exchange.getMessage().setBody(resourceDao.read(id, extractRequestDetails(exchange)));
    }

    private boolean isSearchOperation(Exchange exchange) {
        RequestDetails requestDetails = extractRequestDetails(exchange);
        return requestDetails.getRestOperationType() == RestOperationTypeEnum.SEARCH_TYPE;
    }

    private RequestDetails extractRequestDetails(Exchange exchange) {
        RequestDetails requestDetails = exchange.getIn().getHeader(Constants.FHIR_REQUEST_DETAILS, RequestDetails.class);
        if (requestDetails == null) {
            throw new IllegalStateException("RequestDetails must not be null");
        }
        return requestDetails;
    }
}
