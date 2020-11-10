package org.ehrbase.fhirbridge.camel.processor;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.apache.camel.Exchange;
import org.ehrbase.client.aql.parameter.ParameterValue;
import org.ehrbase.client.aql.query.Query;
import org.ehrbase.client.aql.record.Record1;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.fhirbridge.camel.FhirBridgeHeaders;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.OperationOutcome;
import org.openehealth.ipf.commons.ihe.fhir.Constants;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

import static org.hl7.fhir.r4.model.OperationOutcome.IssueSeverity;
import static org.hl7.fhir.r4.model.OperationOutcome.IssueType;
import static org.hl7.fhir.r4.model.OperationOutcome.OperationOutcomeIssueComponent;

@Component
public class ObservationValidator extends DefaultResourceValidator {

    private final OpenEhrClient openEhrClient;

    public ObservationValidator(OpenEhrClient openEhrClient) {
        this.openEhrClient = openEhrClient;
    }

    @Override
    public void process(Exchange exchange) {
        super.process(exchange);

        FhirContext fhirContext = exchange.getIn().getHeader(Constants.FHIR_CONTEXT, FhirContext.class);
        Observation observation = exchange.getIn().getBody(Observation.class);
        OperationOutcome validationResult = new OperationOutcome();

        String subjectId = observation.getSubject().getIdentifier().getValue();
        if (subjectId == null) {
            validationResult.addIssue(new OperationOutcomeIssueComponent()
                    .setSeverity(IssueSeverity.ERROR)
                    .setCode(IssueType.VALUE)
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
                        .setCode(IssueType.VALUE)
                        .setDiagnostics("No EHR found for subject '" + subjectId + "'")
                        .addExpression("Condition.subject.identifier.value"));
            } else {
                exchange.getMessage().setHeader(FhirBridgeHeaders.EHR_ID, result.get(0).value1());
            }
        }

        if (validationResult.hasIssue()) {
            throw new UnprocessableEntityException(fhirContext, validationResult);
        }
    }
}
