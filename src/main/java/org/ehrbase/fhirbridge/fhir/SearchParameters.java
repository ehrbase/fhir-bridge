package org.ehrbase.fhirbridge.fhir;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.api.Include;
import ca.uhn.fhir.rest.api.SortSpec;
import ca.uhn.fhir.rest.param.TokenParam;
import org.openehealth.ipf.commons.ihe.fhir.FhirSearchParameters;

import java.util.List;
import java.util.Set;

public class SearchParameters implements FhirSearchParameters {

    @Override
    public SortSpec getSortSpec() {
        return null;
    }

    @Override
    public Set<Include> getIncludeSpec() {
        return null;
    }

    @Override
    public List<TokenParam> getPatientIdParam() {
        return null;
    }

    @Override
    public FhirContext getFhirContext() {
        return null;
    }
}
