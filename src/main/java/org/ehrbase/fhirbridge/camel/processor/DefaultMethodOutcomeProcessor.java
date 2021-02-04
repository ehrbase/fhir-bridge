package org.ehrbase.fhirbridge.camel.processor;

import ca.uhn.fhir.rest.api.MethodOutcome;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.fhirbridge.camel.FhirBridgeConstants;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConstants;
import org.ehrbase.fhirbridge.fhir.util.ResourceUtils;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Procedure;

public class DefaultMethodOutcomeProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        MethodOutcome outcome = getMethodOutcome(exchange);
        Identifier versionUid = getIdentifier(exchange);

        IBaseResource resource = outcome.getResource();
        switch (ResourceUtils.getResourceType(resource)) {
            case Condition:
                ((Condition) resource).addIdentifier(versionUid);
                break;
            case DiagnosticReport:
                ((DiagnosticReport) resource).addIdentifier(versionUid);
                break;
            case Observation:
                ((Observation) resource).addIdentifier(versionUid);
                break;
            case Patient:
                ((Patient) resource).addIdentifier(versionUid);
                break;
            case Procedure:
                ((Procedure) resource).addIdentifier(versionUid);
                break;
            default:
                throw new IllegalArgumentException(); // TODO
        }

        exchange.getMessage().setBody(outcome);
    }

    private MethodOutcome getMethodOutcome(Exchange exchange) {
        return exchange.getIn().getHeader(FhirBridgeConstants.METHOD_OUTCOME, MethodOutcome.class);
    }

    private Identifier getIdentifier(Exchange exchange) {
        VersionUid versionUid = exchange.getIn().getHeader(CompositionConstants.VERSION_UID, VersionUid.class);
        return new Identifier()
                .setSystem(versionUid.getSystem())
                .setValue(versionUid.getUuid().toString());
    }
}
