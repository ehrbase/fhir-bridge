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

@Component
public class FhirProfileValidator implements Processor, MessageSourceAware {

    private static final Logger LOG = LoggerFactory.getLogger(FhirProfileValidator.class);

    private final FhirContext fhirContext;

    private MessageSourceAccessor messages;

    public FhirProfileValidator(FhirContext fhirContext) {
        this.fhirContext = fhirContext;
    }

    @Override
    public void process(Exchange exchange) {
        Resource resource = exchange.getIn().getBody(Resource.class);

        LOG.debug("Validating {} resource...", resource.getResourceType());

        List<String> profiles = Resources.getProfileUris(resource);
        if (profiles.isEmpty()) {
            validateDefault(resource, exchange);
        } else {
            validateProfiles(resource, exchange);
        }

        LOG.info("{} resource validated", resource.getResourceType());
    }

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

    private void validateProfiles(Resource resource, Exchange exchange) {
        Set<Profile> supportedProfiles = Profile.resolveAll(resource);
        Class<? extends Resource> resourceType = resource.getClass();

        OperationOutcome outcome = new OperationOutcome();
        if (supportedProfiles.isEmpty()) {
            outcome.addIssue(new OperationOutcomeIssueComponent()
                    .setSeverity(IssueSeverity.FATAL)
                    .setCode(IssueType.VALUE)
                    .setDiagnostics(messages.getMessage("validation.profile.missingSupported", new Object[]{resourceType, Profile.getSupportedProfiles(resourceType)}))
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
