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
import org.hl7.fhir.r4.model.AuditEvent;
import org.hl7.fhir.r4.model.IdType;
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
@SuppressWarnings("java:S107")
public class FindAuditEventProvider extends AbstractPlainProvider {

    @Read(version = true)
    public AuditEvent read(@IdParam IdType id,
                           RequestDetails requestDetails,
                           HttpServletRequest request,
                           HttpServletResponse response) {

        return requestResource(id, null, AuditEvent.class, request, response, requestDetails);
    }

    @Search(type = AuditEvent.class)
    public IBundleProvider search(@OptionalParam(name = IAnyResource.SP_RES_ID) TokenAndListParam id,
                                  @OptionalParam(name = IAnyResource.SP_RES_LANGUAGE) StringAndListParam language,
                                  @OptionalParam(name = Constants.PARAM_LASTUPDATED) DateRangeParam lastUpdated,
                                  @OptionalParam(name = Constants.PARAM_PROFILE) UriAndListParam profile,
                                  @OptionalParam(name = Constants.PARAM_SOURCE) UriAndListParam resourceSource,
                                  @OptionalParam(name = Constants.PARAM_SECURITY) TokenAndListParam security,
                                  @OptionalParam(name = Constants.PARAM_TAG) TokenAndListParam tag,
                                  @OptionalParam(name = Constants.PARAM_CONTENT) StringAndListParam content,
                                  @OptionalParam(name = Constants.PARAM_TEXT) StringAndListParam text,
                                  @OptionalParam(name = Constants.PARAM_FILTER) StringAndListParam filter,
                                  @OptionalParam(name = AuditEvent.SP_ACTION) TokenAndListParam action,
                                  @OptionalParam(name = AuditEvent.SP_ADDRESS) StringAndListParam address,
                                  @OptionalParam(name = AuditEvent.SP_AGENT) ReferenceAndListParam agent,
                                  @OptionalParam(name = AuditEvent.SP_AGENT_NAME) StringAndListParam agentName,
                                  @OptionalParam(name = AuditEvent.SP_AGENT_ROLE) TokenAndListParam agentRole,
                                  @OptionalParam(name = AuditEvent.SP_ALTID) TokenAndListParam altId,
                                  @OptionalParam(name = AuditEvent.SP_DATE) DateRangeParam date,
                                  @OptionalParam(name = AuditEvent.SP_ENTITY) ReferenceAndListParam entity,
                                  @OptionalParam(name = AuditEvent.SP_ENTITY_NAME) StringAndListParam entityName,
                                  @OptionalParam(name = AuditEvent.SP_ENTITY_ROLE) TokenAndListParam entityRole,
                                  @OptionalParam(name = AuditEvent.SP_ENTITY_TYPE) TokenAndListParam entityType,
                                  @OptionalParam(name = AuditEvent.SP_OUTCOME) TokenAndListParam outcome,
                                  @OptionalParam(name = AuditEvent.SP_PATIENT) ReferenceAndListParam patient,
                                  @OptionalParam(name = AuditEvent.SP_POLICY) UriAndListParam policy,
                                  @OptionalParam(name = AuditEvent.SP_SITE) TokenAndListParam site,
                                  @OptionalParam(name = AuditEvent.SP_SOURCE) ReferenceAndListParam source,
                                  @OptionalParam(name = AuditEvent.SP_SUBTYPE) TokenAndListParam subtype,
                                  @OptionalParam(name = AuditEvent.SP_TYPE) TokenAndListParam type,
                                  @Count Integer count, @Offset Integer offset, @Sort SortSpec sort,
                                  RequestDetails requestDetails, HttpServletRequest request, HttpServletResponse response) {

        SearchParameterMap parameters = new SearchParameterMap();

        // Common Parameters
        parameters.add(IAnyResource.SP_RES_ID, id);
        parameters.add(IAnyResource.SP_RES_LANGUAGE, language);
        parameters.add(Constants.PARAM_PROFILE, profile);
        parameters.add(Constants.PARAM_SOURCE, resourceSource);
        parameters.add(Constants.PARAM_SECURITY, security);
        parameters.add(Constants.PARAM_TAG, tag);
        parameters.add(Constants.PARAM_CONTENT, content);
        parameters.add(Constants.PARAM_TEXT, text);
        parameters.add(Constants.PARAM_FILTER, filter);

        // AuditEvent Parameters
        parameters.add(AuditEvent.SP_ACTION, action);
        parameters.add(AuditEvent.SP_ADDRESS, address);
        parameters.add(AuditEvent.SP_AGENT, agent);
        parameters.add(AuditEvent.SP_AGENT_NAME, agentName);
        parameters.add(AuditEvent.SP_AGENT_ROLE, agentRole);
        parameters.add(AuditEvent.SP_ALTID, altId);
        parameters.add(AuditEvent.SP_DATE, date);
        parameters.add(AuditEvent.SP_ENTITY, entity);
        parameters.add(AuditEvent.SP_ENTITY_NAME, entityName);
        parameters.add(AuditEvent.SP_ENTITY_ROLE, entityRole);
        parameters.add(AuditEvent.SP_ENTITY_TYPE, entityType);
        parameters.add(AuditEvent.SP_OUTCOME, outcome);
        parameters.add(AuditEvent.SP_PATIENT, patient);
        parameters.add(AuditEvent.SP_POLICY, policy);
        parameters.add(AuditEvent.SP_SITE, site);
        parameters.add(AuditEvent.SP_SOURCE, source);
        parameters.add(AuditEvent.SP_SUBTYPE, subtype);
        parameters.add(AuditEvent.SP_TYPE, type);
        parameters.setLastUpdated(lastUpdated);
        parameters.setCount(count);
        parameters.setOffset(offset);
        parameters.setSort(sort);

        return requestBundleProvider(parameters, null, ResourceType.AuditEvent.name(), request, response, requestDetails);
    }
}
