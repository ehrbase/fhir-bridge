package org.ehrbase.fhirbridge.fhir.auditevent;

import ca.uhn.fhir.jpa.searchparam.SearchParameterMap;
import ca.uhn.fhir.model.api.Include;
import ca.uhn.fhir.rest.annotation.IncludeParam;
import ca.uhn.fhir.rest.annotation.OptionalParam;
import ca.uhn.fhir.rest.annotation.RequiredParam;
import ca.uhn.fhir.rest.annotation.Search;
import ca.uhn.fhir.rest.annotation.Sort;
import ca.uhn.fhir.rest.api.SortSpec;
import ca.uhn.fhir.rest.api.server.IBundleProvider;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.param.DateRangeParam;
import ca.uhn.fhir.rest.param.TokenAndListParam;
import org.hl7.fhir.r4.model.AuditEvent;
import org.hl7.fhir.r4.model.ResourceType;
import org.openehealth.ipf.commons.ihe.fhir.AbstractPlainProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class FindAuditEventProvider extends AbstractPlainProvider {

    @Search(type = AuditEvent.class)
    public IBundleProvider search(
            @OptionalParam(name = AuditEvent.SP_DATE) DateRangeParam interval,
//            @OptionalParam(name = AuditEvent.SP_PATIENT + ".identifier") TokenAndListParam patientId,
            @OptionalParam(name = AuditEvent.SP_OUTCOME) TokenAndListParam outcome,
            @Sort SortSpec sort,
            @IncludeParam Set<Include> includes,
            RequestDetails requestDetails,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        SearchParameterMap searchParameters = new SearchParameterMap();
        searchParameters.add(AuditEvent.SP_DATE, interval);
        searchParameters.add(AuditEvent.SP_OUTCOME, outcome);
        searchParameters.setSort(sort);
        searchParameters.setIncludes(includes);

        // Run down the route
        IBundleProvider bundleProvide = requestBundleProvider(searchParameters, null, ResourceType.AuditEvent.name(),
                httpServletRequest, httpServletResponse, requestDetails);

        return bundleProvide;
    }
}
