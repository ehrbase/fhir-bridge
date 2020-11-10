package org.ehrbase.fhirbridge.fhir.observation;

import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Observation;
import org.openehealth.ipf.commons.ihe.fhir.AbstractPlainProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReadObservationProvider extends AbstractPlainProvider {

    @Read
    public Observation read(@IdParam IdType id, RequestDetails requestDetails,
                            HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return requestResource(id, null, Observation.class, httpServletRequest, httpServletResponse, requestDetails);
    }
}
