package org.ehrbase.fhirbridge.camel.processor;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.ehrbase.fhirbridge.camel.FhirBridgeHeaders;
import org.ehrbase.fhirbridge.fhir.Profile;
import org.hl7.fhir.r4.model.CanonicalType;
import org.hl7.fhir.r4.model.OperationOutcome;
import org.hl7.fhir.r4.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.r4.model.OperationOutcome.IssueType;
import org.hl7.fhir.r4.model.OperationOutcome.OperationOutcomeIssueComponent;
import org.hl7.fhir.r4.model.Resource;
import org.openehealth.ipf.commons.ihe.fhir.Constants;

import java.util.List;

public class DefaultResourceValidator implements Processor {

    @Override
    public void process(Exchange exchange) {
        FhirContext fhirContext = exchange.getIn().getHeader(Constants.FHIR_CONTEXT, FhirContext.class);
        Resource resource = exchange.getIn().getBody(Resource.class);
        OperationOutcome validationResult = new OperationOutcome();

        if (resource == null) {
            validationResult.addIssue(
                    new OperationOutcomeIssueComponent()
                            .setSeverity(IssueSeverity.FATAL)
                            .setCode(IssueType.INVALID)
                            .setDiagnostics("Observation must not be null"));
            throw new UnprocessableEntityException(fhirContext, validationResult);
        }

        if (!Profile.isDefaultSupported(resource)) {
            List<Profile> supportedProfiles = Profile.resolveAll(resource);
            if (supportedProfiles.isEmpty()) {
                validationResult.addIssue(
                        new OperationOutcomeIssueComponent()
                                .setSeverity(IssueSeverity.FATAL)
                                .setCode(IssueType.VALUE)
                                .setDiagnostics("Default profile for Observation is not supported")
                                .addExpression("Observation.meta.profile"));
                List<CanonicalType> declaredProfiles = resource.getMeta().getProfile();
                if (!declaredProfiles.isEmpty()) {
                    for (int i = 0; i < declaredProfiles.size(); i++) {
                        validationResult.addIssue(
                                new OperationOutcomeIssueComponent()
                                        .setSeverity(IssueSeverity.WARNING)
                                        .setCode(IssueType.VALUE)
                                        .setDiagnostics("Profile not supported: " + declaredProfiles.get(i).getValue())
                                        .addExpression(resource.getResourceType() + ".meta.profile[" + i + "]"));
                    }
                }
                throw new UnprocessableEntityException(fhirContext, validationResult);
            } else if (supportedProfiles.size() > 1) {
                validationResult.addIssue(
                        new OperationOutcomeIssueComponent()
                                .setSeverity(IssueSeverity.FATAL)
                                .setCode(IssueType.VALUE)
                                .setDiagnostics("There is more than one supported profile declared")
                                .addExpression("Observation.meta.profile"));
                throw new UnprocessableEntityException(fhirContext, validationResult);
            } else {
                exchange.getMessage().setHeader(FhirBridgeHeaders.PROFILE, supportedProfiles.get(0));
            }
        }
    }
}
