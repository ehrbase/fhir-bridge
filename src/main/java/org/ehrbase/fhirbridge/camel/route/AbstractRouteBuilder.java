package org.ehrbase.fhirbridge.camel.route;

import ca.uhn.fhir.rest.api.RestOperationTypeEnum;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import org.apache.camel.Predicate;
import org.apache.camel.builder.RouteBuilder;
import org.openehealth.ipf.commons.ihe.fhir.Constants;

public abstract class AbstractRouteBuilder extends RouteBuilder {

    protected Predicate isSearchOperation() {
        return exchange -> {
            RequestDetails requestDetails = exchange.getIn().getHeader(Constants.FHIR_REQUEST_DETAILS, RequestDetails.class);
            return requestDetails.getRestOperationType() == RestOperationTypeEnum.SEARCH_TYPE;
        };
    }
}
