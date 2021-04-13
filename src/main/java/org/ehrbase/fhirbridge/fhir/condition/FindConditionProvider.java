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

package org.ehrbase.fhirbridge.fhir.condition;

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
import ca.uhn.fhir.rest.param.QuantityAndListParam;
import ca.uhn.fhir.rest.param.ReferenceAndListParam;
import ca.uhn.fhir.rest.param.StringAndListParam;
import ca.uhn.fhir.rest.param.TokenAndListParam;
import ca.uhn.fhir.rest.param.UriAndListParam;
import org.hl7.fhir.instance.model.api.IAnyResource;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.ResourceType;
import org.openehealth.ipf.commons.ihe.fhir.AbstractPlainProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Implementation of {@link org.openehealth.ipf.commons.ihe.fhir.FhirProvider FhirProvider} that provides REST support
 * for 'Find Condition' transaction.
 *
 * @since 1.0.0
 */
@SuppressWarnings({"unused", "java:S107", "DuplicatedCode"})
public class FindConditionProvider extends AbstractPlainProvider {

    @Search(type = Condition.class)
    public IBundleProvider searchCondition(@OptionalParam(name = IAnyResource.SP_RES_ID) TokenAndListParam id,
                                           @OptionalParam(name = IAnyResource.SP_RES_LANGUAGE) StringAndListParam language,
                                           @OptionalParam(name = Constants.PARAM_LASTUPDATED) DateRangeParam lastUpdated,
                                           @OptionalParam(name = Constants.PARAM_PROFILE) UriAndListParam profile,
                                           @OptionalParam(name = Constants.PARAM_SOURCE) UriAndListParam source,
                                           @OptionalParam(name = Constants.PARAM_SECURITY) TokenAndListParam security,
                                           @OptionalParam(name = Constants.PARAM_TAG) TokenAndListParam tag,
                                           @OptionalParam(name = Constants.PARAM_CONTENT) StringAndListParam content,
                                           @OptionalParam(name = Constants.PARAM_TEXT) StringAndListParam text,
                                           @OptionalParam(name = Constants.PARAM_FILTER) StringAndListParam filter,
                                           @OptionalParam(name = Condition.SP_ABATEMENT_AGE) QuantityAndListParam abatementAge,
                                           @OptionalParam(name = Condition.SP_ABATEMENT_DATE) DateRangeParam abatementDate,
                                           @OptionalParam(name = Condition.SP_ABATEMENT_STRING) StringAndListParam abatementString,
                                           @OptionalParam(name = Condition.SP_ASSERTER) ReferenceAndListParam asserter,
                                           @OptionalParam(name = Condition.SP_BODY_SITE) TokenAndListParam bodySite,
                                           @OptionalParam(name = Condition.SP_CATEGORY) TokenAndListParam category,
                                           @OptionalParam(name = Condition.SP_CLINICAL_STATUS) TokenAndListParam clinicalStatus,
                                           @OptionalParam(name = Condition.SP_CODE) TokenAndListParam code,
                                           @OptionalParam(name = Condition.SP_ENCOUNTER) ReferenceAndListParam encounter,
                                           @OptionalParam(name = Condition.SP_EVIDENCE) TokenAndListParam evidence,
                                           @OptionalParam(name = Condition.SP_EVIDENCE_DETAIL) ReferenceAndListParam evidenceDetail,
                                           @OptionalParam(name = Condition.SP_IDENTIFIER) TokenAndListParam identifier,
                                           @OptionalParam(name = Condition.SP_ONSET_AGE) QuantityAndListParam onsetAge,
                                           @OptionalParam(name = Condition.SP_ONSET_DATE) DateRangeParam onsetDate,
                                           @OptionalParam(name = Condition.SP_ONSET_INFO) StringAndListParam onsetInfo,
                                           @OptionalParam(name = Condition.SP_PATIENT) ReferenceAndListParam patient,
                                           @OptionalParam(name = Condition.SP_RECORDED_DATE) DateRangeParam recordedDate,
                                           @OptionalParam(name = Condition.SP_SEVERITY) TokenAndListParam severity,
                                           @OptionalParam(name = Condition.SP_STAGE) TokenAndListParam stage,
                                           @OptionalParam(name = Condition.SP_SUBJECT) ReferenceAndListParam subject,
                                           @OptionalParam(name = Condition.SP_VERIFICATION_STATUS) TokenAndListParam verificationStatus,
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

        searchParams.add(Condition.SP_ABATEMENT_AGE, abatementAge);
        searchParams.add(Condition.SP_ABATEMENT_DATE, abatementDate);
        searchParams.add(Condition.SP_ABATEMENT_STRING, abatementString);
        searchParams.add(Condition.SP_ASSERTER, asserter);
        searchParams.add(Condition.SP_BODY_SITE, bodySite);
        searchParams.add(Condition.SP_CATEGORY, category);
        searchParams.add(Condition.SP_CLINICAL_STATUS, clinicalStatus);
        searchParams.add(Condition.SP_CODE, code);
        searchParams.add(Condition.SP_ENCOUNTER, encounter);
        searchParams.add(Condition.SP_EVIDENCE, evidence);
        searchParams.add(Condition.SP_EVIDENCE_DETAIL, evidenceDetail);
        searchParams.add(Condition.SP_IDENTIFIER, identifier);
        searchParams.add(Condition.SP_ONSET_AGE, onsetAge);
        searchParams.add(Condition.SP_ONSET_DATE, onsetDate);
        searchParams.add(Condition.SP_ONSET_INFO, onsetInfo);
        searchParams.add(Condition.SP_PATIENT, patient);
        searchParams.add(Condition.SP_RECORDED_DATE, recordedDate);
        searchParams.add(Condition.SP_SEVERITY, severity);
        searchParams.add(Condition.SP_STAGE, stage);
        searchParams.add(Condition.SP_SUBJECT, subject);
        searchParams.add(Condition.SP_VERIFICATION_STATUS, verificationStatus);

        searchParams.setLastUpdated(lastUpdated);
        searchParams.setCount(count);
        searchParams.setOffset(offset);
        searchParams.setSort(sort);

        return requestBundleProvider(searchParams, null, ResourceType.Condition.name(), request, response, requestDetails);
    }

    @Read(version = true)
    public Condition readCondition(@IdParam IdType id, RequestDetails requestDetails,
                                   HttpServletRequest request, HttpServletResponse response) {
        return requestResource(id, null, Condition.class, request, response, requestDetails);
    }
}
