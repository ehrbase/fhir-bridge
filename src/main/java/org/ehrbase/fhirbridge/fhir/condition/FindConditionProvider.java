package org.ehrbase.fhirbridge.fhir.condition;

import ca.uhn.fhir.rest.annotation.Search;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.ResourceType;
import org.openehealth.ipf.commons.ihe.fhir.AbstractPlainProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FindConditionProvider extends AbstractPlainProvider {

    @Search
    public List<Condition> search(RequestDetails requestDetails,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse) {
        return this.requestBundle(null, null, ResourceType.Condition.name(), httpServletRequest, httpServletResponse, requestDetails);
    }
}
