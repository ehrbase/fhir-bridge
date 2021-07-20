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

package org.ehrbase.fhirbridge.camel.processor;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.ehrbase.fhirbridge.camel.CamelConstants;
import org.ehrbase.fhirbridge.fhir.common.Profile;
import org.ehrbase.fhirbridge.fhir.support.Resources;
import org.hl7.fhir.r4.model.OperationOutcome;
import org.hl7.fhir.r4.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.r4.model.OperationOutcome.IssueType;
import org.hl7.fhir.r4.model.OperationOutcome.OperationOutcomeIssueComponent;
import org.hl7.fhir.r4.model.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * {@link Processor} that validates if the profile is supported by the submitted resource and the application.
 *
 * @since 1.0.0
 */
@Component(FhirProfileValidator.BEAN_ID)
@SuppressWarnings("java:S6212")
public class FhirProfileValidator implements Processor, MessageSourceAware {

    public static final String BEAN_ID = "fhirProfileValidator";

    private static final Logger LOG = LoggerFactory.getLogger(FhirProfileValidator.class);

    private final FhirContext fhirContext;

    private MessageSourceAccessor messages;

    public FhirProfileValidator(FhirContext fhirContext) {
        this.fhirContext = fhirContext;
    }

    @Override
    public void process(Exchange exchange) {
        Resource resource = exchange.getIn().getBody(Resource.class);
        LOG.trace("Validating {} resource...", resource.getResourceType());

        List<String> profiles = Resources.getProfileUris(resource);
        if (profiles.isEmpty()) {
            validateDefault(resource, exchange);
        } else {
            validateProfiles(resource, exchange);
        }

        LOG.info("{} resource validated", resource.getResourceType());
    }

    /**
     * Validates if the default profile is supported by the resource.
     *
     * @param resource the submitted resource
     * @param exchange the current exchange
     */
    private void validateDefault(Resource resource, Exchange exchange) {
        Class<? extends Resource> clazz = resource.getClass();
        Profile profile = Profile.getDefaultProfile(clazz);

        if (profile == null) {
            OperationOutcome outcome = new OperationOutcome()
                    .addIssue(new OperationOutcomeIssueComponent()
                            .setSeverity(IssueSeverity.FATAL)
                            .setCode(IssueType.VALUE)
                            .setDiagnostics(messages.getMessage("validation.profile.defaultNotSupported", new Object[]{resource.getResourceType(), Profile.getSupportedProfiles(clazz)}))
                            .addExpression(profileExpression(resource)));
            throw new UnprocessableEntityException(fhirContext, outcome);
        }
        exchange.getMessage().setHeader(CamelConstants.PROFILE, profile);
    }

    /**
     * Validates the profiles for the given resource.
     *
     * @param resource the submitted resource
     * @param exchange the current exchange
     */
    private void validateProfiles(Resource resource, Exchange exchange) {
        Set<Profile> supportedProfiles = Profile.resolveAll(resource);
        Class<? extends Resource> resourceType = resource.getClass();

        OperationOutcome outcome = new OperationOutcome();
        if (supportedProfiles.isEmpty()) {
            outcome.addIssue(new OperationOutcomeIssueComponent()
                    .setSeverity(IssueSeverity.FATAL)
                    .setCode(IssueType.VALUE)
                    .setDiagnostics(messages.getMessage("validation.profile.missingSupported", new Object[]{Profile.getSupportedProfiles(resourceType)}))
                    .addExpression(profileExpression(resource)));
        } else if (supportedProfiles.size() > 1) {
            outcome.addIssue(new OperationOutcomeIssueComponent()
                    .setSeverity(IssueSeverity.FATAL)
                    .setCode(IssueType.VALUE)
                    .setDiagnostics(messages.getMessage("validation.profile.moreThanOneSupported"))
                    .addExpression(profileExpression(resource)));
        }

        if (outcome.hasIssue()) {
            throw new UnprocessableEntityException(fhirContext, outcome);
        }
        exchange.getMessage().setHeader(CamelConstants.PROFILE, supportedProfiles.iterator().next());
    }

    private String profileExpression(Resource resource) {
        return resource.getResourceType() + ".meta.profile[]";
    }

    @Override
    public void setMessageSource(@NonNull MessageSource messageSource) {
        this.messages = new MessageSourceAccessor(messageSource);
    }
}
