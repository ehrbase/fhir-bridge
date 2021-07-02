package org.ehrbase.fhirbridge.camel.processor;

import ca.uhn.fhir.rest.api.server.RequestDetails;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.util.ObjectHelper;
import org.openehealth.ipf.commons.ihe.fhir.Constants;

public interface FhirRequestProcessor extends Processor {

    default RequestDetails getRequestDetails(Exchange exchange) {
        if (ObjectHelper.isEmpty(exchange.getIn().getHeader(Constants.FHIR_REQUEST_DETAILS))) {
            throw new IllegalArgumentException("RequestDetails must not be null");
        }
        return exchange.getIn().getHeader(Constants.FHIR_REQUEST_DETAILS, RequestDetails.class);
    }
}
