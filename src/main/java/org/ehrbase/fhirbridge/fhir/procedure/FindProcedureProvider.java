package org.ehrbase.fhirbridge.fhir.procedure;

import ca.uhn.fhir.jpa.searchparam.SearchParameterMap;
import ca.uhn.fhir.rest.annotation.Count;
import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.Offset;
import ca.uhn.fhir.rest.annotation.OptionalParam;
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.annotation.Search;
import ca.uhn.fhir.rest.annotation.Sort;
import ca.uhn.fhir.rest.api.Constants;
import ca.uhn.fhir.rest.api.SortSpec;
import ca.uhn.fhir.rest.api.server.IBundleProvider;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.param.DateRangeParam;
import ca.uhn.fhir.rest.param.ReferenceAndListParam;
import ca.uhn.fhir.rest.param.StringAndListParam;
import ca.uhn.fhir.rest.param.TokenAndListParam;
import ca.uhn.fhir.rest.param.UriAndListParam;
import org.hl7.fhir.instance.model.api.IAnyResource;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Procedure;
import org.hl7.fhir.r4.model.ResourceType;
import org.openehealth.ipf.commons.ihe.fhir.AbstractPlainProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Implementation of {@link org.openehealth.ipf.commons.ihe.fhir.FhirProvider FhirProvider} that provides REST support
 * for 'Find Procedure' transaction.
 *
 * @since 1.0.0
 */
@SuppressWarnings({"unused", "java:S107", "DuplicatedCode"})
public class FindProcedureProvider extends AbstractPlainProvider {

    @Search(type = Procedure.class)
    public IBundleProvider searchProcedure(@OptionalParam(name = IAnyResource.SP_RES_ID) TokenAndListParam id,
                                           @OptionalParam(name = IAnyResource.SP_RES_LANGUAGE) StringAndListParam language,
                                           @OptionalParam(name = Constants.PARAM_LASTUPDATED) DateRangeParam lastUpdated,
                                           @OptionalParam(name = Constants.PARAM_PROFILE) UriAndListParam profile,
                                           @OptionalParam(name = Constants.PARAM_SOURCE) UriAndListParam source,
                                           @OptionalParam(name = Constants.PARAM_SECURITY) TokenAndListParam security,
                                           @OptionalParam(name = Constants.PARAM_TAG) TokenAndListParam tag,
                                           @OptionalParam(name = Constants.PARAM_CONTENT) StringAndListParam content,
                                           @OptionalParam(name = Constants.PARAM_TEXT) StringAndListParam text,
                                           @OptionalParam(name = Constants.PARAM_FILTER) StringAndListParam filter,
                                           @OptionalParam(name = Procedure.SP_BASED_ON) ReferenceAndListParam basedOn,
                                           @OptionalParam(name = Procedure.SP_CATEGORY) TokenAndListParam category,
                                           @OptionalParam(name = Procedure.SP_CODE) TokenAndListParam code,
                                           @OptionalParam(name = Procedure.SP_DATE) DateRangeParam date,
                                           @OptionalParam(name = Procedure.SP_ENCOUNTER) ReferenceAndListParam encounter,
                                           @OptionalParam(name = Procedure.SP_IDENTIFIER) TokenAndListParam identifier,
                                           @OptionalParam(name = Procedure.SP_INSTANTIATES_CANONICAL) ReferenceAndListParam instantiatesCanonical,
                                           @OptionalParam(name = Procedure.SP_INSTANTIATES_URI) UriAndListParam instantiatesUri,
                                           @OptionalParam(name = Procedure.SP_LOCATION) ReferenceAndListParam location,
                                           @OptionalParam(name = Procedure.SP_PART_OF) ReferenceAndListParam partOf,
                                           @OptionalParam(name = Procedure.SP_PATIENT) ReferenceAndListParam patient,
                                           @OptionalParam(name = Procedure.SP_PERFORMER) ReferenceAndListParam performer,
                                           @OptionalParam(name = Procedure.SP_REASON_CODE) TokenAndListParam reasonCode,
                                           @OptionalParam(name = Procedure.SP_REASON_REFERENCE) ReferenceAndListParam reasonReference,
                                           @OptionalParam(name = Procedure.SP_STATUS) TokenAndListParam status,
                                           @OptionalParam(name = Procedure.SP_SUBJECT) ReferenceAndListParam subject,
                                           @Count Integer count, @Offset Integer offset, @Sort SortSpec sort,
                                           RequestDetails requestDetails, HttpServletRequest request, HttpServletResponse response) {

        SearchParameterMap searchParams = new SearchParameterMap();
        searchParams.add(IAnyResource.SP_RES_ID, id);
        searchParams.add(IAnyResource.SP_RES_LANGUAGE, language);

        searchParams.add(Constants.PARAM_PROFILE, profile);
        searchParams.add(Constants.PARAM_SOURCE, source);
        searchParams.add(Constants.PARAM_SECURITY, security);
        searchParams.add(Constants.PARAM_TAG, tag);
        searchParams.add(Constants.PARAM_CONTENT, content);
        searchParams.add(Constants.PARAM_TEXT, text);
        searchParams.add(Constants.PARAM_FILTER, filter);

        searchParams.add(Procedure.SP_BASED_ON, basedOn);
        searchParams.add(Procedure.SP_CATEGORY, category);
        searchParams.add(Procedure.SP_CODE, code);
        searchParams.add(Procedure.SP_DATE, date);
        searchParams.add(Procedure.SP_ENCOUNTER, encounter);
        searchParams.add(Procedure.SP_IDENTIFIER, identifier);
        searchParams.add(Procedure.SP_INSTANTIATES_CANONICAL, instantiatesCanonical);
        searchParams.add(Procedure.SP_INSTANTIATES_URI, instantiatesUri);
        searchParams.add(Procedure.SP_LOCATION, location);
        searchParams.add(Procedure.SP_PART_OF, partOf);
        searchParams.add(Procedure.SP_PATIENT, patient);
        searchParams.add(Procedure.SP_PERFORMER, performer);
        searchParams.add(Procedure.SP_REASON_CODE, reasonCode);
        searchParams.add(Procedure.SP_REASON_REFERENCE, reasonReference);
        searchParams.add(Procedure.SP_STATUS, status);
        searchParams.add(Procedure.SP_SUBJECT, subject);

        searchParams.setLastUpdated(lastUpdated);
        searchParams.setCount(count);
        searchParams.setOffset(offset);
        searchParams.setSort(sort);

        return requestBundleProvider(searchParams, null, ResourceType.Procedure.name(), request, response, requestDetails);
    }

    @Read(version = true)
    public Procedure readProcedure(@IdParam IdType id, RequestDetails requestDetails,
                                   HttpServletRequest request, HttpServletResponse response) {
        return requestResource(id, null, Procedure.class, request, response, requestDetails);
    }
}
