package org.ehrbase.fhirbridge.fhir.condition;

import ca.uhn.fhir.rest.annotation.Search;
import ca.uhn.fhir.rest.annotation.Sort;
import ca.uhn.fhir.rest.api.SortSpec;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import org.ehrbase.fhirbridge.fhir.SearchParameters;
import org.hl7.fhir.r4.model.Condition;
import org.openehealth.ipf.commons.ihe.fhir.AbstractPlainProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SearchConditionProvider extends AbstractPlainProvider {

    @Search
    @SuppressWarnings("unused")
    public List<Condition> search(
            @Sort SortSpec sort,
            RequestDetails requestDetails,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        SearchParameters parameters = new SearchParameters();

        return this.requestBundle(null, parameters, "Condition", httpServletRequest, httpServletResponse, requestDetails);
    }
}
