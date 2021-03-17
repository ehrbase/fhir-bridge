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

package org.ehrbase.fhirbridge.fhir.observation;

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
import ca.uhn.fhir.rest.param.CompositeAndListParam;
import ca.uhn.fhir.rest.param.DateParam;
import ca.uhn.fhir.rest.param.DateRangeParam;
import ca.uhn.fhir.rest.param.QuantityAndListParam;
import ca.uhn.fhir.rest.param.QuantityParam;
import ca.uhn.fhir.rest.param.ReferenceAndListParam;
import ca.uhn.fhir.rest.param.StringAndListParam;
import ca.uhn.fhir.rest.param.StringParam;
import ca.uhn.fhir.rest.param.TokenAndListParam;
import ca.uhn.fhir.rest.param.TokenParam;
import ca.uhn.fhir.rest.param.UriAndListParam;
import org.hl7.fhir.instance.model.api.IAnyResource;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.ResourceType;
import org.openehealth.ipf.commons.ihe.fhir.AbstractPlainProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Implementation of {@link org.openehealth.ipf.commons.ihe.fhir.FhirProvider FhirProvider} that provides REST support
 * for 'Find Observation' transaction.
 *
 * @since 1.0.0
 */
@SuppressWarnings({"unused", "java:S107", "DuplicatedCode"})
public class FindObservationProvider extends AbstractPlainProvider {

    @Search(type = Observation.class)
    public IBundleProvider searchObservation(@OptionalParam(name = IAnyResource.SP_RES_ID) TokenAndListParam id,
                                             @OptionalParam(name = IAnyResource.SP_RES_LANGUAGE) StringAndListParam language,
                                             @OptionalParam(name = Constants.PARAM_LASTUPDATED) DateRangeParam lastUpdated,
                                             @OptionalParam(name = Constants.PARAM_PROFILE) UriAndListParam profile,
                                             @OptionalParam(name = Constants.PARAM_SOURCE) UriAndListParam source,
                                             @OptionalParam(name = Constants.PARAM_SECURITY) TokenAndListParam security,
                                             @OptionalParam(name = Constants.PARAM_TAG) TokenAndListParam tag,
                                             @OptionalParam(name = Constants.PARAM_CONTENT) StringAndListParam content,
                                             @OptionalParam(name = Constants.PARAM_TEXT) StringAndListParam text,
                                             @OptionalParam(name = Constants.PARAM_FILTER) StringAndListParam filter,
                                             @OptionalParam(name = Observation.SP_BASED_ON) ReferenceAndListParam basedOn,
                                             @OptionalParam(name = Observation.SP_CATEGORY) TokenAndListParam category,
                                             @OptionalParam(name = Observation.SP_CODE) TokenAndListParam code,
                                             @OptionalParam(name = Observation.SP_CODE_VALUE_CONCEPT, compositeTypes = {TokenParam.class, TokenParam.class})
                                                     CompositeAndListParam<TokenParam, TokenParam> codeValueConcept,
                                             @OptionalParam(name = Observation.SP_CODE_VALUE_DATE, compositeTypes = {TokenParam.class, DateParam.class})
                                                     CompositeAndListParam<TokenParam, DateParam> codeValueDate,
                                             @OptionalParam(name = Observation.SP_CODE_VALUE_QUANTITY, compositeTypes = {TokenParam.class, QuantityParam.class})
                                                     CompositeAndListParam<TokenParam, QuantityParam> codeValueQuantity,
                                             @OptionalParam(name = Observation.SP_CODE_VALUE_STRING, compositeTypes = {TokenParam.class, StringParam.class})
                                                     CompositeAndListParam<TokenParam, StringParam> codeValueString,
                                             @OptionalParam(name = Observation.SP_COMBO_CODE) TokenAndListParam comboCode,
                                             @OptionalParam(name = Observation.SP_COMBO_CODE_VALUE_CONCEPT, compositeTypes = {TokenParam.class, TokenParam.class})
                                                     CompositeAndListParam<TokenParam, TokenParam> comboCodeValueConcept,
                                             @OptionalParam(name = Observation.SP_COMBO_CODE_VALUE_QUANTITY, compositeTypes = {TokenParam.class, QuantityParam.class})
                                                     CompositeAndListParam<TokenParam, QuantityParam> comboCodeValueQuantity,
                                             @OptionalParam(name = Observation.SP_COMBO_DATA_ABSENT_REASON) TokenAndListParam comboDataAbsentReason,
                                             @OptionalParam(name = Observation.SP_COMBO_CODE_VALUE_CONCEPT) TokenAndListParam comboValueConcept,
                                             @OptionalParam(name = Observation.SP_COMBO_CODE_VALUE_QUANTITY) QuantityAndListParam comboValueQuantity,
                                             @OptionalParam(name = Observation.SP_COMPONENT_CODE) TokenAndListParam componentCode,
                                             @OptionalParam(name = Observation.SP_COMPONENT_CODE_VALUE_CONCEPT, compositeTypes = {TokenParam.class, TokenParam.class})
                                                     CompositeAndListParam<TokenParam, TokenParam> componentCodeValueConcept,
                                             @OptionalParam(name = Observation.SP_COMPONENT_CODE_VALUE_QUANTITY, compositeTypes = {TokenParam.class, QuantityParam.class})
                                                     CompositeAndListParam<TokenParam, QuantityParam> componentCodeValueEntity,
                                             @OptionalParam(name = Observation.SP_COMPONENT_DATA_ABSENT_REASON) TokenAndListParam componentDataAbsentReason,
                                             @OptionalParam(name = Observation.SP_COMPONENT_VALUE_CONCEPT) TokenAndListParam componentValueConcept,
                                             @OptionalParam(name = Observation.SP_COMPONENT_CODE_VALUE_QUANTITY) QuantityAndListParam componentValueQuantity,
                                             @OptionalParam(name = Observation.SP_DATA_ABSENT_REASON) TokenAndListParam dataAbsentReason,
                                             @OptionalParam(name = Observation.SP_DATE) DateRangeParam date,
                                             @OptionalParam(name = Observation.SP_DERIVED_FROM) ReferenceAndListParam derivedFrom,
                                             @OptionalParam(name = Observation.SP_DEVICE) ReferenceAndListParam device,
                                             @OptionalParam(name = Observation.SP_ENCOUNTER) ReferenceAndListParam encounter,
                                             @OptionalParam(name = Observation.SP_FOCUS) ReferenceAndListParam focus,
                                             @OptionalParam(name = Observation.SP_HAS_MEMBER) ReferenceAndListParam hasMember,
                                             @OptionalParam(name = Observation.SP_IDENTIFIER) TokenAndListParam identifier,
                                             @OptionalParam(name = Observation.SP_METHOD) TokenAndListParam method,
                                             @OptionalParam(name = Observation.SP_PART_OF) ReferenceAndListParam partOf,
                                             @OptionalParam(name = Observation.SP_PATIENT) ReferenceAndListParam patient,
                                             @OptionalParam(name = Observation.SP_PERFORMER) ReferenceAndListParam performer,
                                             @OptionalParam(name = Observation.SP_SPECIMEN) ReferenceAndListParam specimen,
                                             @OptionalParam(name = Observation.SP_STATUS) TokenAndListParam status,
                                             @OptionalParam(name = Observation.SP_SUBJECT) ReferenceAndListParam subject,
                                             @OptionalParam(name = Observation.SP_VALUE_CONCEPT) TokenAndListParam valueConcept,
                                             @OptionalParam(name = Observation.SP_VALUE_DATE) DateRangeParam valueDate,
                                             @OptionalParam(name = Observation.SP_VALUE_QUANTITY) QuantityAndListParam valueQuantity,
                                             @OptionalParam(name = Observation.SP_VALUE_STRING) StringAndListParam valueString,
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

        searchParams.add(Observation.SP_BASED_ON, basedOn);
        searchParams.add(Observation.SP_CATEGORY, category);
        searchParams.add(Observation.SP_CODE, code);
        searchParams.add(Observation.SP_CODE_VALUE_CONCEPT, codeValueConcept);
        searchParams.add(Observation.SP_CODE_VALUE_DATE, codeValueDate);
        searchParams.add(Observation.SP_CODE_VALUE_QUANTITY, codeValueQuantity);
        searchParams.add(Observation.SP_CODE_VALUE_STRING, codeValueString);
        searchParams.add(Observation.SP_COMBO_CODE, comboCode);
        searchParams.add(Observation.SP_COMBO_CODE_VALUE_CONCEPT, comboCodeValueConcept);
        searchParams.add(Observation.SP_COMBO_CODE_VALUE_QUANTITY, comboCodeValueQuantity);
        searchParams.add(Observation.SP_COMBO_DATA_ABSENT_REASON, comboDataAbsentReason);
        searchParams.add(Observation.SP_COMBO_CODE_VALUE_CONCEPT, comboValueConcept);
        searchParams.add(Observation.SP_COMBO_CODE_VALUE_QUANTITY, comboValueQuantity);
        searchParams.add(Observation.SP_COMPONENT_CODE, componentCode);
        searchParams.add(Observation.SP_COMPONENT_CODE_VALUE_CONCEPT, componentCodeValueConcept);
        searchParams.add(Observation.SP_COMPONENT_CODE_VALUE_QUANTITY, componentCodeValueEntity);
        searchParams.add(Observation.SP_COMPONENT_DATA_ABSENT_REASON, componentDataAbsentReason);
        searchParams.add(Observation.SP_COMPONENT_VALUE_CONCEPT, componentValueConcept);
        searchParams.add(Observation.SP_COMPONENT_CODE_VALUE_QUANTITY, componentValueQuantity);
        searchParams.add(Observation.SP_DATA_ABSENT_REASON, dataAbsentReason);
        searchParams.add(Observation.SP_DATE, date);
        searchParams.add(Observation.SP_DERIVED_FROM, derivedFrom);
        searchParams.add(Observation.SP_DEVICE, device);
        searchParams.add(Observation.SP_ENCOUNTER, encounter);
        searchParams.add(Observation.SP_FOCUS, focus);
        searchParams.add(Observation.SP_HAS_MEMBER, hasMember);
        searchParams.add(Observation.SP_IDENTIFIER, identifier);
        searchParams.add(Observation.SP_METHOD, method);
        searchParams.add(Observation.SP_PART_OF, partOf);
        searchParams.add(Observation.SP_PATIENT, patient);
        searchParams.add(Observation.SP_PERFORMER, performer);
        searchParams.add(Observation.SP_SPECIMEN, specimen);
        searchParams.add(Observation.SP_STATUS, status);
        searchParams.add(Observation.SP_SUBJECT, subject);
        searchParams.add(Observation.SP_VALUE_CONCEPT, valueConcept);
        searchParams.add(Observation.SP_VALUE_DATE, valueDate);
        searchParams.add(Observation.SP_VALUE_QUANTITY, valueQuantity);
        searchParams.add(Observation.SP_VALUE_STRING, valueString);

        searchParams.setLastUpdated(lastUpdated);
        searchParams.setCount(count);
        searchParams.setOffset(offset);
        searchParams.setSort(sort);

        return requestBundleProvider(searchParams, null, ResourceType.Observation.name(), request, response, requestDetails);
    }

    @Read(version = true)
    public Observation readObservation(@IdParam IdType id, RequestDetails requestDetails,
                                       HttpServletRequest request, HttpServletResponse response) {
        return requestResource(id, null, Observation.class, request, response, requestDetails);
    }
}
