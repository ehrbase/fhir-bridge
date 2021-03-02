package org.ehrbase.fhirbridge.fhir.condition;

import ca.uhn.fhir.jpa.searchparam.SearchParameterMap;
import ca.uhn.fhir.rest.annotation.Count;
import ca.uhn.fhir.rest.annotation.Offset;
import ca.uhn.fhir.rest.annotation.RequiredParam;
import ca.uhn.fhir.rest.annotation.Search;
import ca.uhn.fhir.rest.annotation.Sort;
import ca.uhn.fhir.rest.api.SortSpec;
import ca.uhn.fhir.rest.api.server.IBundleProvider;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.param.ReferenceAndListParam;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.ResourceType;
import org.openehealth.ipf.commons.ihe.fhir.AbstractPlainProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Implementation of {@link org.openehealth.ipf.commons.ihe.fhir.FhirProvider FhirProvider} for Find Condition transaction.
 *
 * @since 1.0.0
 */
public class FindConditionProvider extends AbstractPlainProvider {

    @Search(type = Condition.class)
    public IBundleProvider search(@RequiredParam(name = Condition.SP_SUBJECT, chainWhitelist = {Patient.SP_IDENTIFIER}) ReferenceAndListParam subject,
                                  @Count Integer count,
                                  @Offset Integer offset,
                                  @Sort SortSpec sort,
                                  RequestDetails requestDetails,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse) {

        SearchParameterMap searchParams = new SearchParameterMap();
        searchParams.add(Condition.SP_SUBJECT, subject);
        searchParams.setCount(count);
        searchParams.setOffset(offset);
        searchParams.setSort(sort);

        return requestBundleProvider(searchParams, null, ResourceType.Condition.name(), httpServletRequest, httpServletResponse, requestDetails);
    }
}
