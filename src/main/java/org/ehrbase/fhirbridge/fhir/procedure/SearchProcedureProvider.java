package org.ehrbase.fhirbridge.fhir.procedure;

import ca.uhn.fhir.rest.annotation.Search;
import ca.uhn.fhir.rest.annotation.Sort;
import ca.uhn.fhir.rest.api.SortSpec;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import org.ehrbase.fhirbridge.fhir.SearchParameters;
import org.hl7.fhir.r4.model.Procedure;
import org.openehealth.ipf.commons.ihe.fhir.AbstractPlainProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SearchProcedureProvider extends AbstractPlainProvider {

    @Search
    @SuppressWarnings("unused")
    public List<Procedure> search(
            @Sort SortSpec sort,
            RequestDetails requestDetails,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        SearchParameters parameters = new SearchParameters(getFhirContext());
        parameters.setSort(sort);

        return this.requestBundle(null, parameters, "Procedure", httpServletRequest, httpServletResponse, requestDetails);
    }
}
