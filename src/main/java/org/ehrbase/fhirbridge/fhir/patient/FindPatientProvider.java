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

package org.ehrbase.fhirbridge.fhir.patient;

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
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.ResourceType;
import org.openehealth.ipf.commons.ihe.fhir.AbstractPlainProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Implementation of {@link org.openehealth.ipf.commons.ihe.fhir.FhirProvider FhirProvider} that provides REST support
 * for 'Find Patient' transaction.
 *
 * @since 1.0.0
 */
@SuppressWarnings({"unused", "java:S107", "DuplicatedCode"})
public class FindPatientProvider extends AbstractPlainProvider {

    @Search(type = Patient.class)
    public IBundleProvider searchPatient(@OptionalParam(name = IAnyResource.SP_RES_ID) TokenAndListParam id,
                                         @OptionalParam(name = IAnyResource.SP_RES_LANGUAGE) StringAndListParam resourceLanguage,
                                         @OptionalParam(name = Constants.PARAM_LASTUPDATED) DateRangeParam lastUpdated,
                                         @OptionalParam(name = Constants.PARAM_PROFILE) UriAndListParam profile,
                                         @OptionalParam(name = Constants.PARAM_SOURCE) UriAndListParam source,
                                         @OptionalParam(name = Constants.PARAM_SECURITY) TokenAndListParam security,
                                         @OptionalParam(name = Constants.PARAM_TAG) TokenAndListParam tag,
                                         @OptionalParam(name = Constants.PARAM_CONTENT) StringAndListParam content,
                                         @OptionalParam(name = Constants.PARAM_TEXT) StringAndListParam text,
                                         @OptionalParam(name = Constants.PARAM_FILTER) StringAndListParam filter,
                                         @OptionalParam(name = Patient.SP_ACTIVE) TokenAndListParam active,
                                         @OptionalParam(name = Patient.SP_ADDRESS) StringAndListParam address,
                                         @OptionalParam(name = Patient.SP_ADDRESS_CITY) StringAndListParam addressCity,
                                         @OptionalParam(name = Patient.SP_ADDRESS_COUNTRY) StringAndListParam addressCountry,
                                         @OptionalParam(name = Patient.SP_ADDRESS_POSTALCODE) StringAndListParam addressPostalCode,
                                         @OptionalParam(name = Patient.SP_ADDRESS_STATE) StringAndListParam addressState,
                                         @OptionalParam(name = Patient.SP_ADDRESS_USE) TokenAndListParam addressUse,
                                         @OptionalParam(name = Patient.SP_BIRTHDATE) DateRangeParam birthdate,
                                         @OptionalParam(name = Patient.SP_DEATH_DATE) DateRangeParam deathDate,
                                         @OptionalParam(name = Patient.SP_DECEASED) TokenAndListParam deceased,
                                         @OptionalParam(name = Patient.SP_EMAIL) TokenAndListParam email,
                                         @OptionalParam(name = Patient.SP_FAMILY) StringAndListParam family,
                                         @OptionalParam(name = Patient.SP_GENDER) TokenAndListParam gender,
                                         @OptionalParam(name = Patient.SP_GENERAL_PRACTITIONER) ReferenceAndListParam generalPractitioner,
                                         @OptionalParam(name = Patient.SP_GIVEN) StringAndListParam given,
                                         @OptionalParam(name = Patient.SP_IDENTIFIER) TokenAndListParam identifier,
                                         @OptionalParam(name = Patient.SP_LANGUAGE) TokenAndListParam language,
                                         @OptionalParam(name = Patient.SP_LINK) ReferenceAndListParam link,
                                         @OptionalParam(name = Patient.SP_NAME) StringAndListParam name,
                                         @OptionalParam(name = Patient.SP_ORGANIZATION) ReferenceAndListParam organization,
                                         @OptionalParam(name = Patient.SP_PHONE) TokenAndListParam phone,
                                         @OptionalParam(name = Patient.SP_PHONETIC) StringAndListParam phonetic,
                                         @OptionalParam(name = Patient.SP_TELECOM) TokenAndListParam telecom,
                                         @Count Integer count, @Offset Integer offset, @Sort SortSpec sort,
                                         RequestDetails requestDetails, HttpServletRequest request, HttpServletResponse response) {

        SearchParameterMap searchParams = new SearchParameterMap();
        searchParams.add(IAnyResource.SP_RES_ID, id);
        searchParams.add(IAnyResource.SP_RES_LANGUAGE, resourceLanguage);

        searchParams.add(Constants.PARAM_PROFILE, profile);
        searchParams.add(Constants.PARAM_SOURCE, source);
        searchParams.add(Constants.PARAM_SECURITY, security);
        searchParams.add(Constants.PARAM_TAG, tag);
        searchParams.add(Constants.PARAM_CONTENT, content);
        searchParams.add(Constants.PARAM_TEXT, text);
        searchParams.add(Constants.PARAM_FILTER, filter);

        searchParams.add(Patient.SP_ACTIVE, active);
        searchParams.add(Patient.SP_ADDRESS, address);
        searchParams.add(Patient.SP_ADDRESS_CITY, addressCity);
        searchParams.add(Patient.SP_ADDRESS_COUNTRY, addressCountry);
        searchParams.add(Patient.SP_ADDRESS_POSTALCODE, addressPostalCode);
        searchParams.add(Patient.SP_ADDRESS_STATE, addressState);
        searchParams.add(Patient.SP_ADDRESS_USE, addressUse);
        searchParams.add(Patient.SP_BIRTHDATE, birthdate);
        searchParams.add(Patient.SP_DEATH_DATE, deathDate);
        searchParams.add(Patient.SP_DECEASED, deceased);
        searchParams.add(Patient.SP_EMAIL, email);
        searchParams.add(Patient.SP_FAMILY, family);
        searchParams.add(Patient.SP_GENDER, gender);
        searchParams.add(Patient.SP_GENERAL_PRACTITIONER, generalPractitioner);
        searchParams.add(Patient.SP_GIVEN, given);
        searchParams.add(Patient.SP_IDENTIFIER, identifier);
        searchParams.add(Patient.SP_LANGUAGE, language);
        searchParams.add(Patient.SP_LINK, link);
        searchParams.add(Patient.SP_NAME, name);
        searchParams.add(Patient.SP_ORGANIZATION, organization);
        searchParams.add(Patient.SP_PHONE, phone);
        searchParams.add(Patient.SP_PHONETIC, phonetic);
        searchParams.add(Patient.SP_TELECOM, telecom);

        searchParams.setLastUpdated(lastUpdated);
        searchParams.setCount(count);
        searchParams.setOffset(offset);
        searchParams.setSort(sort);

        return requestBundleProvider(searchParams, null, ResourceType.Patient.name(), request, response, requestDetails);
    }

    @Read(version = true)
    public Patient findPatient(@IdParam IdType id, RequestDetails requestDetails,
                               HttpServletRequest request, HttpServletResponse response) {
        return requestResource(id, null, Patient.class, request, response, requestDetails);
    }
}
