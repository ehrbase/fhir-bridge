package org.ehrbase.fhirbridge.fhir.encounter;

import ca.uhn.fhir.jpa.searchparam.SearchParameterMap;
import ca.uhn.fhir.rest.annotation.Count;
import ca.uhn.fhir.rest.annotation.Offset;
import ca.uhn.fhir.rest.annotation.RequiredParam;
import ca.uhn.fhir.rest.annotation.Search;
import ca.uhn.fhir.rest.annotation.Sort;
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.api.SortSpec;
import ca.uhn.fhir.rest.api.server.IBundleProvider;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.param.ReferenceAndListParam;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Encounter;
import org.hl7.fhir.r4.model.ResourceType;
import org.openehealth.ipf.commons.ihe.fhir.AbstractPlainProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Implementation of {@link org.openehealth.ipf.commons.ihe.fhir.FhirProvider FhirProvider} that provides REST support
 * for 'Find Encounter' transaction.
 *
 * @since 1.0.0
 */
public class FindEncounterProvider extends AbstractPlainProvider {

    @Search(type = Encounter.class)
    @SuppressWarnings("unused")
    public IBundleProvider searchEncounter(@RequiredParam(name = Encounter.SP_SUBJECT) ReferenceAndListParam subject,
                                           @Count Integer count, @Offset Integer offset, @Sort SortSpec sort,
                                           RequestDetails requestDetails, HttpServletRequest request, HttpServletResponse response) {
        SearchParameterMap searchParams = new SearchParameterMap();
        searchParams.add(Encounter.SP_SUBJECT, subject);
        searchParams.setCount(count);
        searchParams.setOffset(offset);
        searchParams.setSort(sort);
        return requestBundleProvider(searchParams, null, ResourceType.Encounter.name(), request, response, requestDetails);
    }

    @Read(version = true)
    @SuppressWarnings("unused")
    public Encounter readEncounter(@IdParam IdType id, RequestDetails requestDetails,
                                       HttpServletRequest request, HttpServletResponse response) {
        return requestResource(id, null, Encounter.class, request, response, requestDetails);
    }
}
