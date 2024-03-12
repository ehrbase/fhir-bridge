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

package org.ehrbase.fhirbridge.fhir.specimen;

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
import org.hl7.fhir.r4.model.ResourceType;
import org.hl7.fhir.r4.model.Specimen;
import org.openehealth.ipf.commons.ihe.fhir.AbstractPlainProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Implementation of {@link org.openehealth.ipf.commons.ihe.fhir.FhirProvider FhirProvider} that provides REST support
 * for 'Find Specimen' transaction.
 *
 * @since 1.0.0
 */
@SuppressWarnings({"unused", "java:S107", "DuplicatedCode"})
public class FindSpecimenProvider extends AbstractPlainProvider {

    @Search(type = Specimen.class)
    public IBundleProvider searchSpecimen(@OptionalParam(name = IAnyResource.SP_RES_ID) TokenAndListParam id,
                                             @OptionalParam(name = IAnyResource.SP_RES_LANGUAGE) StringAndListParam language,
                                             @OptionalParam(name = Constants.PARAM_LASTUPDATED) DateRangeParam lastUpdated,
                                             @OptionalParam(name = Constants.PARAM_PROFILE) UriAndListParam profile,
                                             @OptionalParam(name = Constants.PARAM_SOURCE) UriAndListParam source,
                                             @OptionalParam(name = Constants.PARAM_SECURITY) TokenAndListParam security,
                                             @OptionalParam(name = Constants.PARAM_TAG) TokenAndListParam tag,
                                             @OptionalParam(name = Constants.PARAM_CONTENT) StringAndListParam content,
                                             @OptionalParam(name = Constants.PARAM_TEXT) StringAndListParam text,
                                             @OptionalParam(name = Constants.PARAM_FILTER) StringAndListParam filter,
                                             @OptionalParam(name = Specimen.SP_ACCESSION) TokenAndListParam accession,
                                             @OptionalParam(name = Specimen.SP_IDENTIFIER) TokenAndListParam identifier,
                                             @OptionalParam(name = Specimen.SP_PATIENT) ReferenceAndListParam patient,
                                             @OptionalParam(name = Specimen.SP_STATUS) TokenAndListParam status,
                                             @OptionalParam(name = Specimen.SP_BODYSITE) TokenAndListParam bodysite,
                                             @OptionalParam(name = Specimen.SP_COLLECTED) DateRangeParam collected,
                                             @OptionalParam(name = Specimen.SP_COLLECTOR)  ReferenceAndListParam collector,
                                             @OptionalParam(name = Specimen.SP_CONTAINER) TokenAndListParam container,
                                             @OptionalParam(name = Specimen.SP_CONTAINER_ID)  TokenAndListParam containerIdentifier,
                                             @OptionalParam(name = Specimen.SP_PARENT)  ReferenceAndListParam parent,
                                             @OptionalParam(name = Specimen.SP_TYPE) TokenAndListParam type,
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

        searchParams.add(Specimen.SP_ACCESSION, accession);
        searchParams.add(Specimen.SP_IDENTIFIER, identifier);
        searchParams.add(Specimen.SP_PATIENT, patient);
        searchParams.add(Specimen.SP_STATUS, status);
        searchParams.add(Specimen.SP_BODYSITE, bodysite);
        searchParams.add(Specimen.SP_COLLECTED, collected);
        searchParams.add(Specimen.SP_COLLECTOR, collector);
        searchParams.add(Specimen.SP_CONTAINER, container);
        searchParams.add(Specimen.SP_CONTAINER_ID, containerIdentifier);
        searchParams.add(Specimen.SP_PARENT, parent);
        searchParams.add(Specimen.SP_TYPE, type);

        searchParams.setLastUpdated(lastUpdated);
        searchParams.setCount(count);
        searchParams.setOffset(offset);
        searchParams.setSort(sort);

        return requestBundleProvider(searchParams, null, ResourceType.Specimen.name(), request, response, requestDetails);
    }

    @Read(version = true)
    public Specimen readSpecimen(@IdParam IdType id, RequestDetails requestDetails,
                                       HttpServletRequest request, HttpServletResponse response) {
        return requestResource(id, null, Specimen.class, request, response, requestDetails);
    }
}
