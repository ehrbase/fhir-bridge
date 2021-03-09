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

package org.ehrbase.fhirbridge.fhir.auditevent;

import ca.uhn.fhir.jpa.searchparam.SearchParameterMap;
import ca.uhn.fhir.rest.annotation.Count;
import ca.uhn.fhir.rest.annotation.Offset;
import ca.uhn.fhir.rest.annotation.OptionalParam;
import ca.uhn.fhir.rest.annotation.Search;
import ca.uhn.fhir.rest.annotation.Sort;
import ca.uhn.fhir.rest.api.SortSpec;
import ca.uhn.fhir.rest.api.server.IBundleProvider;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.param.DateRangeParam;
import ca.uhn.fhir.rest.param.ReferenceAndListParam;
import ca.uhn.fhir.rest.param.TokenAndListParam;
import org.hl7.fhir.r4.model.AuditEvent;
import org.hl7.fhir.r4.model.ResourceType;
import org.openehealth.ipf.commons.ihe.fhir.AbstractPlainProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Implementation of {@link org.openehealth.ipf.commons.ihe.fhir.FhirProvider FhirProvider} that provides REST support
 * for 'Find Audit Event' transaction.
 *
 * @since 1.0.0
 */
public class FindAuditEventProvider extends AbstractPlainProvider {

    @Search(type = AuditEvent.class)
    @SuppressWarnings({"unused", "java:S107"})
    public IBundleProvider searchAuditEvent(@OptionalParam(name = AuditEvent.SP_DATE) DateRangeParam interval,
                                            @OptionalParam(name = AuditEvent.SP_ENTITY) ReferenceAndListParam entity,
                                            @OptionalParam(name = AuditEvent.SP_OUTCOME) TokenAndListParam outcome,
                                            @Count Integer count, @Offset Integer offset, @Sort SortSpec sort,
                                            RequestDetails requestDetails, HttpServletRequest request, HttpServletResponse response) {
        SearchParameterMap searchParams = new SearchParameterMap();
        searchParams.add(AuditEvent.SP_DATE, interval);
        searchParams.add(AuditEvent.SP_ENTITY, entity);
        searchParams.add(AuditEvent.SP_OUTCOME, outcome);
        searchParams.setCount(count);
        searchParams.setOffset(offset);
        searchParams.setSort(sort);
        return requestBundleProvider(searchParams, null, ResourceType.AuditEvent.name(), request, response, requestDetails);
    }
}
