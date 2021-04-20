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
package org.ehrbase.fhirbridge.security;

import ca.uhn.fhir.rest.api.RestOperationTypeEnum;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.server.interceptor.auth.AuthorizationInterceptor;
import ca.uhn.fhir.rest.server.interceptor.auth.IAuthRule;
import ca.uhn.fhir.rest.server.interceptor.auth.RuleBuilder;
import org.apache.commons.lang3.StringUtils;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.UrlType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.ArrayList;
import java.util.List;

/**
 * This interceptor changes the behavior of a @{@link ca.uhn.fhir.rest.server.RestfulServer}
 * instance by adding authorisation rules based on the existence of OAuth claims as defined
 * by SMART on FHIR specifications.
 * See HAPI FHIR project documentation to understand how this access control mechanism works.
 */
public class SmartOnFhirAuthorizationInterceptor extends AuthorizationInterceptor {

    @Override
    public List<IAuthRule> buildRuleList(RequestDetails theRequestDetails) {
        List<IAuthRule> rules = new ArrayList<>();

        //allow unconditional access to metadata requests
        if (theRequestDetails.getRestOperationType() == RestOperationTypeEnum.METADATA) {
            return new RuleBuilder().allowAll("SOF_allow_all").build();
        }

        Jwt jwt =
                ((JwtAuthenticationToken) SecurityContextHolder
                        .getContext()
                        .getAuthentication())
                        .getToken();

        //no jwt: no rules that would allow authorisation
        //we cannot even arrive at this point if spring oauth support is enabled:
        //callers would just get a 401
        if (jwt != null) {
            String smartOnFhirPatientId = jwt.getClaim("patient");
            if (StringUtils.isNotEmpty(smartOnFhirPatientId)) {
                addSmartOFPatientRules(smartOnFhirPatientId, rules);
                addSmartOFWildcardAccess(smartOnFhirPatientId, rules, Condition.class);
                rules.addAll(new RuleBuilder().denyAll("rule_deny_resource_if_no_SOF_patId_claim").build());
            } else { //non smart on fhir logic
                rules.addAll(new RuleBuilder().allowAll("allow_all_operations_for_all_resources").build());
            }
        }
        return rules;
    }

    /**
     * Allos read and write access to all Patients resources for a particular patient Id
     * In FHIR terms, if the resource's Patient compartment matches the provided id, then
     * read and write operations can take place.
     *
     * @param pSmartOnFhirPatientId The patient identifier a resource should have to be allowed
     *                              for read or write access.
     * @param rules                 A list of rules to add to. Rules based on the provided patient id will be
     *                              added to this collection.
     */
    private void addSmartOFPatientRules(String pSmartOnFhirPatientId, List<IAuthRule> rules) {
        //no create rule -> should be done by keycloak registration at the moment
        IdType sofId = new IdType(Patient.class.getSimpleName(), pSmartOnFhirPatientId);
        rules.addAll(buildReadRule("rule_read_own_sof_patient_resource", Patient.class, Patient.class.getSimpleName(), sofId));
        rules.addAll(buildWriteRule("rule_update_own_sof_patient_resource", Patient.class, Patient.class.getSimpleName(), sofId));
    }

    /*Allows wildcard access for CRUD operations for a
     *particular FHIR resource type. Read access allows search, as long as there is a subject
     * criteria that matches the patient provided in the access token.
     * @param pSmartOnFhirPatientId The value from the patient claim of OAuth2 access token
     * @param rules The collection to which rules are added. These will be used for access
     * control later by the HAPI FHIR pipeline
     * @param pResourceType The FHIR resource type for which the wildcard access will apply.
     */
    private void addSmartOFWildcardAccess(String pSmartOnFhirPatientId,
                                          List<IAuthRule> rules,
                                          Class<? extends Resource> pResourceType) {
        //TODO: HARDCODED BASE DEMOGRAPHIC URL, USE ACCESS TOKEN
        //you MUST use an UrlType, or search will be blocked.
        IdType sofId = new IdType(new UrlType("http://localhost:8082/fhir/Patient/" + pSmartOnFhirPatientId));
        rules
                .addAll(
                        buildCreateRule(
                                "rule_create_own_sof_" + pResourceType.getSimpleName() + "_resource",
                                pResourceType,
                                Patient.class.getSimpleName(),
                                sofId));
        rules
                .addAll(
                        buildReadRule(
                                "rule_read_own_sof_" + pResourceType.getSimpleName() + "_resource",
                                pResourceType,
                                Patient.class.getSimpleName(),
                                sofId));
        rules
                .addAll(
                        buildWriteRule(
                                "rule_write_own_sof_" + pResourceType.getSimpleName() + "_resource",
                                pResourceType,
                                Patient.class.getSimpleName(),
                                sofId));
        rules
                .addAll(
                        buildDeleteRule(
                                "rule_delete_own_sof_" + pResourceType.getSimpleName() + "_resource",
                                pResourceType,
                                Patient.class.getSimpleName(),
                                sofId));
    }

    private List<IAuthRule> buildReadRule(String name,
                                          Class<? extends IBaseResource> resource,
                                          String compartmentName,
                                          IdType compartmentId) {
        return new RuleBuilder()
                .allow(name)
                .read()
                .resourcesOfType(resource)
                .inCompartment("Patient", compartmentId)
                .build();
    }

    private List<IAuthRule> buildCreateRule(String name,
                                            Class<? extends IBaseResource> resource,
                                            String pCompartmentName,
                                            IdType pCompartmentId) {
        return new RuleBuilder()
                .allow(name)
                .create()
                .resourcesOfType(resource)
                .inCompartment(pCompartmentName, pCompartmentId)
                .build();
    }

    private List<IAuthRule> buildWriteRule(String name,
                                           Class<? extends IBaseResource> resource,
                                           String pCompartmentName,
                                           IdType pCompartmentId) {
        return new RuleBuilder()
                .allow(name)
                .write()
                .resourcesOfType(resource)
                .inCompartment(pCompartmentName, pCompartmentId)
                .build();
    }

    private List<IAuthRule> buildDeleteRule(String name,
                                            Class<? extends IBaseResource> resource,
                                            String pCompartmentName,
                                            IdType pCompartmentId) {
        return new RuleBuilder()
                .allow(name)
                .delete()
                .resourcesOfType(resource)
                .inCompartment(pCompartmentName, pCompartmentId)
                .build();
    }
}
