package org.ehrbase.fhirbridge.fhir.condition;

import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.IdType;
import org.openehealth.ipf.commons.ihe.fhir.AbstractPlainProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReadConditionProvider extends AbstractPlainProvider {

    @Read
    public Condition read(@IdParam IdType id, RequestDetails requestDetails,
                          HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return requestResource(id, null, Condition.class, httpServletRequest, httpServletResponse, requestDetails);
    }
}
