/*
 * Copyright 2020-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ehrbase.fhirbridge.fhir.consent;

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
import org.hl7.fhir.r4.model.Consent;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.ResourceType;
import org.openehealth.ipf.commons.ihe.fhir.AbstractPlainProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Implementation of {@link org.openehealth.ipf.commons.ihe.fhir.FhirProvider FhirProvider} that provides REST support
 * for 'Find Consent' transaction.
 *
 * @since 1.0.0
 */
@SuppressWarnings({"unused", "java:S107", "DuplicatedCode"})
public class FindConsentProvider extends AbstractPlainProvider {

    @Search(type = Consent.class)
    public IBundleProvider searchConsent(@OptionalParam(name = IAnyResource.SP_RES_ID) TokenAndListParam id,
                                         @OptionalParam(name = IAnyResource.SP_RES_LANGUAGE) StringAndListParam language,
                                         @OptionalParam(name = Constants.PARAM_LASTUPDATED) DateRangeParam lastUpdated,
                                         @OptionalParam(name = Constants.PARAM_PROFILE) UriAndListParam profile,
                                         @OptionalParam(name = Constants.PARAM_SOURCE) UriAndListParam resourceSource,
                                         @OptionalParam(name = Constants.PARAM_SECURITY) TokenAndListParam security,
                                         @OptionalParam(name = Constants.PARAM_TAG) TokenAndListParam tag,
                                         @OptionalParam(name = Constants.PARAM_CONTENT) StringAndListParam content,
                                         @OptionalParam(name = Constants.PARAM_TEXT) StringAndListParam text,
                                         @OptionalParam(name = Constants.PARAM_FILTER) StringAndListParam filter,
                                         @OptionalParam(name = Consent.SP_ACTION) TokenAndListParam action,
                                         @OptionalParam(name = Consent.SP_ACTOR) ReferenceAndListParam actor,
                                         @OptionalParam(name = Consent.SP_CATEGORY) TokenAndListParam category,
                                         @OptionalParam(name = Consent.SP_CONSENTOR) ReferenceAndListParam consentor,
                                         @OptionalParam(name = Consent.SP_DATA) ReferenceAndListParam data,
                                         @OptionalParam(name = Consent.SP_DATE) DateRangeParam date,
                                         @OptionalParam(name = Consent.SP_IDENTIFIER) TokenAndListParam identifier,
                                         @OptionalParam(name = Consent.SP_ORGANIZATION) ReferenceAndListParam organization,
                                         @OptionalParam(name = Consent.SP_PATIENT) ReferenceAndListParam patient,
                                         @OptionalParam(name = Consent.SP_PERIOD) DateRangeParam period,
                                         @OptionalParam(name = Consent.SP_PURPOSE) TokenAndListParam purpose,
                                         @OptionalParam(name = Consent.SP_SCOPE) TokenAndListParam scope,
                                         @OptionalParam(name = Consent.SP_SECURITY_LABEL) TokenAndListParam securityLabel,
                                         @OptionalParam(name = Consent.SP_SOURCE_REFERENCE) ReferenceAndListParam sourceReference,
                                         @OptionalParam(name = Consent.SP_STATUS) TokenAndListParam status,
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

        searchParams.add(Consent.SP_ACTION, action);
        searchParams.add(Consent.SP_ACTOR, actor);
        searchParams.add(Consent.SP_CATEGORY, category);
        searchParams.add(Consent.SP_CONSENTOR, consentor);
        searchParams.add(Consent.SP_DATA, data);
        searchParams.add(Consent.SP_DATE, date);
        searchParams.add(Consent.SP_IDENTIFIER, identifier);
        searchParams.add(Consent.SP_ORGANIZATION, organization);
        searchParams.add(Consent.SP_PATIENT, patient);
        searchParams.add(Consent.SP_PERIOD, period);
        searchParams.add(Consent.SP_PURPOSE, purpose);
        searchParams.add(Consent.SP_SCOPE, scope);
        searchParams.add(Consent.SP_SECURITY_LABEL, securityLabel);
        searchParams.add(Consent.SP_SOURCE_REFERENCE, sourceReference);
        searchParams.add(Consent.SP_STATUS, status);

        searchParams.setLastUpdated(lastUpdated);
        searchParams.setCount(count);
        searchParams.setOffset(offset);
        searchParams.setSort(sort);

        return requestBundleProvider(searchParams, null, ResourceType.Consent.name(), request, response, requestDetails);
    }

    @Read(version = true)
    public Consent readConsent(@IdParam IdType id, RequestDetails requestDetails,
                               HttpServletRequest request, HttpServletResponse response) {
        return requestResource(id, null, Consent.class, request, response, requestDetails);
    }
}
