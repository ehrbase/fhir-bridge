package org.ehrbase.fhirbridge.camel.processor;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.ehrbase.client.aql.parameter.ParameterValue;
import org.ehrbase.client.aql.query.Query;
import org.ehrbase.client.aql.record.Record1;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.fhirbridge.camel.FhirBridgeHeaders;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.OperationOutcome;
import org.hl7.fhir.r4.model.Procedure;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.ResourceType;

import java.util.List;
import java.util.UUID;

public class PatientIdProcessor implements Processor {

    private final FhirContext fhirContext;

    private final OpenEhrClient openEhrClient;

    public PatientIdProcessor(FhirContext fhirContext, OpenEhrClient openEhrClient) {
        this.fhirContext = fhirContext;
        this.openEhrClient = openEhrClient;
    }

    @Override
    public void process(Exchange exchange) {
        Resource resource = exchange.getIn().getBody(Resource.class);

        Query<Record1<UUID>> query = Query.buildNativeQuery("select e/ehr_id/value from ehr e where e/ehr_status/subject/external_ref/id/value = $patientId", UUID.class);
        List<Record1<UUID>> result = openEhrClient.aqlEndpoint()
                .execute(query, new ParameterValue<>("patientId", extractPatientId(resource)));
        if (result.isEmpty()) {
            throw new ResourceNotFoundException("Test");
        }

        exchange.getIn().setHeader(FhirBridgeHeaders.EHR_ID, result.get(0).value1());
    }

    private String extractPatientId(Resource resource) {
        String patientId;
        ResourceType resourceType = resource.getResourceType();

        switch (resourceType) {
            case Condition:
                patientId = ((Condition) resource).getSubject().getIdentifier().getValue();
                break;
            case DiagnosticReport:
                patientId = ((DiagnosticReport) resource).getSubject().getIdentifier().getValue();
                break;
            case Observation:
                patientId = ((Observation) resource).getSubject().getIdentifier().getValue();
                break;
            case Procedure:
                patientId = ((Procedure) resource).getSubject().getIdentifier().getValue();
                break;
            default:
                throw new IllegalArgumentException("Unsupported resource [" + resourceType + "]");
        }
//        if (patientId == null) {
//            throw new UnprocessableEntityException(fhirContext, new OperationOutcome()
//                    .addIssue(new OperationOutcome.OperationOutcomeIssueComponent()
//                            .setSeverity(OperationOutcome.IssueSeverity.FATAL)
//                            .setCode(OperationOutcome.IssueType.VALUE)
//                            .setDiagnostics(messages.getMessage("validation.subject.identifierRequired"))
//                            .addExpression(resource + ".subject.identifier")));
//        }
        return patientId;
    }
}
