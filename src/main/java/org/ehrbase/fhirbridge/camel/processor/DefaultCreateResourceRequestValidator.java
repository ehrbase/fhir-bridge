package org.ehrbase.fhirbridge.camel.processor;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import ca.uhn.fhir.validation.FhirValidator;
import ca.uhn.fhir.validation.ValidationResult;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.ehrbase.fhirbridge.camel.FhirBridgeConstants;
import org.ehrbase.fhirbridge.fhir.common.Profile;
import org.hl7.fhir.r4.model.CanonicalType;
import org.hl7.fhir.r4.model.OperationOutcome;
import org.hl7.fhir.r4.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.r4.model.OperationOutcome.IssueType;
import org.hl7.fhir.r4.model.OperationOutcome.OperationOutcomeIssueComponent;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.ResourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.lang.NonNull;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultCreateResourceRequestValidator implements Processor, MessageSourceAware {

    private final Logger LOG = LoggerFactory.getLogger(DefaultCreateResourceRequestValidator.class);

    private final FhirContext fhirContext;

    private final FhirValidator fhirValidator;

    private MessageSourceAccessor messages;

    public DefaultCreateResourceRequestValidator(FhirContext fhirContext, FhirValidator fhirValidator) {
        this.fhirContext = fhirContext;
        this.fhirValidator = fhirValidator;
    }

    @Override
    public void process(Exchange exchange) {
        Resource resource = exchange.getIn().getBody(Resource.class);

        LOG.debug("Start validating {} resource...", resource.getResourceType());

        validateProfile(exchange, resource);
        validateResource(resource);

        LOG.info("{} resource validated", resource.getResourceType());
    }

    private void validateProfile(Exchange exchange, Resource resource) {
        OperationOutcome operationOutcome = new OperationOutcome();

        if (!Profile.isDefaultSupported(resource)) {
            ResourceType resourceType = resource.getResourceType();
            Collection<Profile> supportedProfiles = Profile.resolve(resource);
            List<String> declaredProfiles = resource.getMeta().getProfile()
                    .stream()
                    .map(CanonicalType::getValue)
                    .collect(Collectors.toList());

            if (supportedProfiles.isEmpty()) {
                for (int i = 0; i < declaredProfiles.size(); i++) {
                    operationOutcome.addIssue(new OperationOutcomeIssueComponent()
                            .setSeverity(IssueSeverity.WARNING)
                            .setCode(IssueType.VALUE)
                            .setDiagnostics(messages.getMessage("validation.profile.notSupported", new Object[]{declaredProfiles.get(i), resourceType}))
                            .addExpression(resource.getResourceType() + ".meta.profile[" + i + "]"));
                }
                operationOutcome.addIssue(new OperationOutcomeIssueComponent()
                        .setSeverity(IssueSeverity.FATAL)
                        .setCode(IssueType.VALUE)
                        .setDiagnostics(messages.getMessage("validation.profile.defaultNotSupported", new Object[]{resourceType, Profile.getAllSupportedProfileUris(resource)}))
                        .addExpression(resource.getResourceType() + ".meta.profile[]"));
            } else if (supportedProfiles.size() > 1) {
                for (int i = 0; i < declaredProfiles.size(); i++) {
                    operationOutcome.addIssue(new OperationOutcomeIssueComponent()
                            .setSeverity(IssueSeverity.INFORMATION)
                            .setCode(IssueType.VALUE)
                            .setDiagnostics(messages.getMessage("validation.profile.supported", new Object[]{declaredProfiles.get(i), resourceType}))
                            .addExpression(resource.getResourceType() + ".meta.profile[" + i + "]"));
                }
                operationOutcome.addIssue(new OperationOutcomeIssueComponent()
                        .setSeverity(IssueSeverity.FATAL)
                        .setCode(IssueType.VALUE)
                        .setDiagnostics(messages.getMessage("validation.profile.moreThanOneSupported"))
                        .addExpression(resource.getResourceType() + ".meta.profile[]"));
            }

            if (operationOutcome.hasIssue()) {
                throw new UnprocessableEntityException(fhirContext, operationOutcome);
            }

            exchange.getMessage().setHeader(FhirBridgeConstants.PROFILE, supportedProfiles.iterator().next());
        }
    }

    private void validateResource(Resource resource) {
        ValidationResult validationResult = fhirValidator.validateWithResult(resource);
        if (!validationResult.isSuccessful()) {
            throw new UnprocessableEntityException(fhirContext, validationResult.toOperationOutcome());
        }
    }

    @Override
    public void setMessageSource(@NonNull MessageSource messageSource) {
        this.messages = new MessageSourceAccessor(messageSource);
    }
}
