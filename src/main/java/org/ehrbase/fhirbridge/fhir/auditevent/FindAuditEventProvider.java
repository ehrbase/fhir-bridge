package org.ehrbase.fhirbridge.fhir.auditevent;

import ca.uhn.fhir.jpa.searchparam.SearchParameterMap;
import ca.uhn.fhir.model.api.Include;
import ca.uhn.fhir.rest.annotation.IncludeParam;
import ca.uhn.fhir.rest.annotation.Search;
import ca.uhn.fhir.rest.annotation.Sort;
import ca.uhn.fhir.rest.api.SortSpec;
import ca.uhn.fhir.rest.api.server.IBundleProvider;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import org.hl7.fhir.r4.model.AuditEvent;
import org.hl7.fhir.r4.model.ResourceType;
import org.openehealth.ipf.commons.ihe.fhir.AbstractPlainProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class FindAuditEventProvider extends AbstractPlainProvider {

    @Search(type = AuditEvent.class)
    public IBundleProvider auditSearch(
//            @RequiredParam(name = AuditEvent.SP_DATE) DateRangeParam interval,
//            @OptionalParam(name = AuditEvent.SP_ADDRESS) StringAndListParam address,
//            @OptionalParam(name = AuditEvent.SP_AGENT) ReferenceParam agent,
//            @OptionalParam(name = AuditEvent.SP_PATIENT + ".identifier") TokenAndListParam patientId,
//            @OptionalParam(name = AuditEvent.SP_ENTITY) ReferenceParam entity,
//            @OptionalParam(name = AuditEvent.SP_ENTITY_TYPE) TokenAndListParam entityType,
//            @OptionalParam(name = AuditEvent.SP_ENTITY_ROLE) TokenAndListParam entityRole,
//            @OptionalParam(name = AuditEvent.SP_SOURCE) ReferenceParam source,
//            @OptionalParam(name = AuditEvent.SP_TYPE) TokenAndListParam type,
//            @OptionalParam(name = AuditEvent.SP_SUBTYPE) TokenAndListParam subtype,
//            @OptionalParam(name = AuditEvent.SP_OUTCOME) TokenAndListParam outcome,
            @Sort SortSpec sort,
            @IncludeParam Set<Include> includes,
            RequestDetails requestDetails,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

//        var searchParameters = Iti81SearchParameters.builder()
//                .interval(interval)
//                .address(address)
//                .agent(agent)
//                .patientId(patientId)
//                .entity(entity)
//                .entityType(entityType)
//                .entityRole(entityRole)
//                .source(source)
//                .type(type)
//                .subtype(subtype)
//                .outcome(outcome)
//                .sortSpec(sortSpec)
//                .includeSpec(includeSpec)
//                .fhirContext(getFhirContext())
//                .build();

        SearchParameterMap searchParameters = new SearchParameterMap();
        searchParameters.setSort(sort);
        searchParameters.setIncludes(includes);

        // Run down the route
        IBundleProvider bundleProvide = requestBundleProvider(searchParameters, null, ResourceType.AuditEvent.name(),
                httpServletRequest, httpServletResponse, requestDetails);

        return bundleProvide;
    }
}
