package org.ehrbase.fhirbridge.camel.processor;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.ehrbase.client.aql.parameter.ParameterValue;
import org.ehrbase.client.aql.query.Query;
import org.ehrbase.client.aql.record.Record1;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.fhirbridge.camel.FhirBridgeHeaders;
import org.ehrbase.fhirbridge.fhir.Profile;
import org.hl7.fhir.r4.model.CanonicalType;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.OperationOutcome;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

import static org.hl7.fhir.r4.model.OperationOutcome.IssueSeverity;
import static org.hl7.fhir.r4.model.OperationOutcome.IssueType;
import static org.hl7.fhir.r4.model.OperationOutcome.OperationOutcomeIssueComponent;

@Component
public class ObservationValidator implements Processor {

    private final OpenEhrClient openEhrClient;

    public ObservationValidator(OpenEhrClient openEhrClient) {
        this.openEhrClient = openEhrClient;
    }

    @Override
    public void process(Exchange exchange) {
        Observation observation = exchange.getIn().getBody(Observation.class);

        OperationOutcome validationResult = new OperationOutcome();

        if (observation == null) {
            validationResult.addIssue(new OperationOutcomeIssueComponent()
                    .setSeverity(IssueSeverity.FATAL)
                    .setCode(IssueType.INVALID)
                    .setDiagnostics("Observation must not be null"));
            throw new UnprocessableEntityException("Fatal error", validationResult);
        }

        List<Profile> supportedProfiles = Profile.resolveAll(observation);
        if (supportedProfiles.isEmpty() && !Profile.isDefaultSupported(observation)) {
            validationResult.addIssue(new OperationOutcomeIssueComponent()
                    .setSeverity(IssueSeverity.FATAL)
                    .setCode(IssueType.INVALID)
                    .setDiagnostics("Default profile for Observation is not supported")
                    .addExpression("Observation.meta.profile"));
            List<CanonicalType> declaredProfiles = observation.getMeta().getProfile();
            if (!declaredProfiles.isEmpty()) {
                for (int i = 0; i < declaredProfiles.size(); i++) {
                    validationResult.addIssue(new OperationOutcomeIssueComponent()
                            .setSeverity(IssueSeverity.WARNING)
                            .setCode(IssueType.INVALID)
                            .setDiagnostics("Profile not supported: " + declaredProfiles.get(i).getValue())
                            .addExpression("Observation.meta.profile[" + i + "]"));
                }
            }
            throw new UnprocessableEntityException("Fatal error", validationResult);
        } else if (supportedProfiles.size() > 1) {
            validationResult.addIssue(new OperationOutcomeIssueComponent()
                    .setSeverity(IssueSeverity.FATAL)
                    .setCode(IssueType.INVALID)
                    .setDiagnostics("There is more than one supported profile declared")
                    .addExpression("Observation.meta.profile"));
            throw new UnprocessableEntityException("Fatal error", validationResult);
        } else if (supportedProfiles.size() == 1) {
            exchange.getMessage().setHeader(FhirBridgeHeaders.PROFILE, supportedProfiles.get(0));
        }

        String subjectId = observation.getSubject().getIdentifier().getValue();
        if (subjectId == null) {
            validationResult.addIssue(new OperationOutcomeIssueComponent()
                    .setSeverity(IssueSeverity.ERROR)
                    .setCode(IssueType.BUSINESSRULE)
                    .setDiagnostics("Field is required")
                    .addExpression("Condition.subject.identifier.value"));
        } else {
            Query<Record1<UUID>> query = Query.buildNativeQuery(
                    "select e/ehr_id/value from ehr e where e/ehr_status/subject/external_ref/id/value = $subjectId", UUID.class);
            List<Record1<UUID>> result = openEhrClient.aqlEndpoint()
                    .execute(query, new ParameterValue<>("subjectId", subjectId));

            if (result.isEmpty()) {
                validationResult.addIssue(new OperationOutcomeIssueComponent()
                        .setSeverity(IssueSeverity.ERROR)
                        .setCode(IssueType.BUSINESSRULE)
                        .setDiagnostics("No EHR found for subject '" + subjectId + "'")
                        .addExpression("Condition.subject.identifier.value"));
            } else {
                exchange.getMessage().setHeader(FhirBridgeHeaders.EHR_ID, result.get(0).value1());
            }
        }

        if (validationResult.hasIssue()) {
            throw new UnprocessableEntityException("Validation failed", validationResult);
        }
    }
}
