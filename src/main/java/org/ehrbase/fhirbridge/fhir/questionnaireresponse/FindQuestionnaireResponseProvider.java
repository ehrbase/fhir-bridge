package org.ehrbase.fhirbridge.fhir.questionnaireresponse;

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
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.hl7.fhir.r4.model.ResourceType;
import org.openehealth.ipf.commons.ihe.fhir.AbstractPlainProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Implementation of {@link org.openehealth.ipf.commons.ihe.fhir.FhirProvider FhirProvider} that provides REST support
 * for 'Find Questionnaire-Response' transaction.
 *
 * @since 1.0.0
 */
@SuppressWarnings({"unused", "java:S107", "DuplicatedCode"})
public class FindQuestionnaireResponseProvider extends AbstractPlainProvider {

    @Search(type = QuestionnaireResponse.class)
    public IBundleProvider searchQuestionnaireResponse(@OptionalParam(name = IAnyResource.SP_RES_ID) TokenAndListParam id,
                                                       @OptionalParam(name = IAnyResource.SP_RES_LANGUAGE) StringAndListParam language,
                                                       @OptionalParam(name = Constants.PARAM_LASTUPDATED) DateRangeParam lastUpdated,
                                                       @OptionalParam(name = Constants.PARAM_PROFILE) UriAndListParam profile,
                                                       @OptionalParam(name = Constants.PARAM_SOURCE) UriAndListParam resourceSource,
                                                       @OptionalParam(name = Constants.PARAM_SECURITY) TokenAndListParam security,
                                                       @OptionalParam(name = Constants.PARAM_TAG) TokenAndListParam tag,
                                                       @OptionalParam(name = Constants.PARAM_CONTENT) StringAndListParam content,
                                                       @OptionalParam(name = Constants.PARAM_TEXT) StringAndListParam text,
                                                       @OptionalParam(name = Constants.PARAM_FILTER) StringAndListParam filter,
                                                       @OptionalParam(name = QuestionnaireResponse.SP_AUTHOR) ReferenceAndListParam author,
                                                       @OptionalParam(name = QuestionnaireResponse.SP_AUTHORED) DateRangeParam authored,
                                                       @OptionalParam(name = QuestionnaireResponse.SP_BASED_ON) ReferenceAndListParam basedOn,
                                                       @OptionalParam(name = QuestionnaireResponse.SP_ENCOUNTER) ReferenceAndListParam encounter,
                                                       @OptionalParam(name = QuestionnaireResponse.SP_IDENTIFIER) TokenAndListParam identifier,
                                                       @OptionalParam(name = QuestionnaireResponse.SP_PART_OF) ReferenceAndListParam partOf,
                                                       @OptionalParam(name = QuestionnaireResponse.SP_PATIENT) ReferenceAndListParam patient,
                                                       @OptionalParam(name = QuestionnaireResponse.SP_QUESTIONNAIRE) ReferenceAndListParam questionnaire,
                                                       @OptionalParam(name = QuestionnaireResponse.SP_SOURCE) ReferenceAndListParam source,
                                                       @OptionalParam(name = QuestionnaireResponse.SP_STATUS) TokenAndListParam status,
                                                       @OptionalParam(name = QuestionnaireResponse.SP_SUBJECT) ReferenceAndListParam subject,
                                                       @Count Integer count, @Offset Integer offset, @Sort SortSpec sort,
                                                       RequestDetails requestDetails, HttpServletRequest request, HttpServletResponse response) {

        SearchParameterMap searchParams = new SearchParameterMap();
        searchParams.add(IAnyResource.SP_RES_ID, id);
        searchParams.add(IAnyResource.SP_RES_LANGUAGE, language);

        searchParams.add(Constants.PARAM_PROFILE, profile);
        searchParams.add(Constants.PARAM_SOURCE, resourceSource);
        searchParams.add(Constants.PARAM_SECURITY, security);
        searchParams.add(Constants.PARAM_TAG, tag);
        searchParams.add(Constants.PARAM_CONTENT, content);
        searchParams.add(Constants.PARAM_TEXT, text);
        searchParams.add(Constants.PARAM_FILTER, filter);

        searchParams.add(QuestionnaireResponse.SP_AUTHOR, author);
        searchParams.add(QuestionnaireResponse.SP_AUTHORED, authored);
        searchParams.add(QuestionnaireResponse.SP_BASED_ON, basedOn);
        searchParams.add(QuestionnaireResponse.SP_ENCOUNTER, encounter);
        searchParams.add(QuestionnaireResponse.SP_IDENTIFIER, identifier);
        searchParams.add(QuestionnaireResponse.SP_PART_OF, partOf);
        searchParams.add(QuestionnaireResponse.SP_PATIENT, patient);
        searchParams.add(QuestionnaireResponse.SP_QUESTIONNAIRE, questionnaire);
        searchParams.add(QuestionnaireResponse.SP_SOURCE, source);
        searchParams.add(QuestionnaireResponse.SP_STATUS, status);
        searchParams.add(QuestionnaireResponse.SP_SUBJECT, subject);

        searchParams.setLastUpdated(lastUpdated);
        searchParams.setCount(count);
        searchParams.setOffset(offset);
        searchParams.setSort(sort);

        return requestBundleProvider(searchParams, null, ResourceType.QuestionnaireResponse.name(), request, response, requestDetails);
    }

    @Read(version = true)
    public QuestionnaireResponse readQuestionnaireResponse(@IdParam IdType id, RequestDetails requestDetails,
                                                           HttpServletRequest request, HttpServletResponse response) {
        return requestResource(id, null, QuestionnaireResponse.class, request, response, requestDetails);
    }
}
