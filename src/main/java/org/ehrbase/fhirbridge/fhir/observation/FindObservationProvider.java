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
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.annotation.RequiredParam;
import ca.uhn.fhir.rest.annotation.Search;
import ca.uhn.fhir.rest.annotation.Sort;
import ca.uhn.fhir.rest.api.SortSpec;
import ca.uhn.fhir.rest.api.server.IBundleProvider;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.param.ReferenceAndListParam;
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
public class FindObservationProvider extends AbstractPlainProvider {

    @Search(type = Observation.class)
    @SuppressWarnings("unused")
    public IBundleProvider searchObservation(@RequiredParam(name = Observation.SP_SUBJECT) ReferenceAndListParam subject,
                                             @Count Integer count, @Offset Integer offset, @Sort SortSpec sort,
                                             RequestDetails requestDetails, HttpServletRequest request, HttpServletResponse response) {
        SearchParameterMap searchParams = new SearchParameterMap();
        searchParams.add(Observation.SP_SUBJECT, subject);
        searchParams.setCount(count);
        searchParams.setOffset(offset);
        searchParams.setSort(sort);
        return requestBundleProvider(searchParams, null, ResourceType.Observation.name(), request, response, requestDetails);
    }

    @Read(version = true)
    @SuppressWarnings("unused")
    public Observation readObservation(@IdParam IdType id, RequestDetails requestDetails,
                                       HttpServletRequest request, HttpServletResponse response) {
        return requestResource(id, null, Observation.class, request, response, requestDetails);
    }
}
