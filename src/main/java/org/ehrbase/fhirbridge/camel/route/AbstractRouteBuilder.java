package org.ehrbase.fhirbridge.camel.route;

import ca.uhn.fhir.rest.api.RestOperationTypeEnum;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import org.apache.camel.Predicate;
import org.apache.camel.builder.RouteBuilder;
import org.openehealth.ipf.commons.ihe.fhir.Constants;
import org.springframework.beans.factory.annotation.Value;

public abstract class AbstractRouteBuilder extends RouteBuilder {

    @Value("${fhir-bridge.debug.enabled:false}")
    private boolean debug;

    @Override
    public void configure() throws Exception {
        errorHandler(defaultErrorHandler()
                .logStackTrace(debug)
                .logExhaustedMessageHistory(debug));

        onException(Exception.class)
                .process("defaultExceptionHandler");
    }

    protected Predicate isSearchOperation() {
        return exchange -> {
            RequestDetails requestDetails = exchange.getIn().getHeader(Constants.FHIR_REQUEST_DETAILS, RequestDetails.class);
            return requestDetails.getRestOperationType() == RestOperationTypeEnum.SEARCH_TYPE;
        };
    }
}
