package org.ehrbase.fhirbridge.camel.processor;

import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.server.exceptions.InternalErrorException;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.fhirbridge.camel.FhirBridgeConstants;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConstants;
import org.ehrbase.fhirbridge.fhir.support.Resources;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Resource;
import org.springframework.stereotype.Component;

/**
 * {@link Processor} that injects the composition version ID in the resource sent back to the client.
 */
@Component
public class ResourceResponseProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        MethodOutcome methodOutcome = getMethodOutcome(exchange);
        VersionUid compositionVersionId = getCompositionVersionId(exchange);

        Resource resource = (Resource) methodOutcome.getResource();
        Identifier identifier = new Identifier()
                .setSystem(compositionVersionId.getSystem())
                .setValue(compositionVersionId.getUuid().toString());
        Resources.addIdentifier(identifier, resource);

        exchange.getMessage().setBody(methodOutcome);
    }

    private MethodOutcome getMethodOutcome(Exchange exchange) {
        MethodOutcome methodOutcome = exchange.getIn().getHeader(FhirBridgeConstants.METHOD_OUTCOME, MethodOutcome.class);
        if (methodOutcome == null) {
            throw new InternalErrorException("MethodOutcome must not be null");
        }
        return methodOutcome;
    }

    private VersionUid getCompositionVersionId(Exchange exchange) {
        VersionUid compositionVersionId = exchange.getIn().getHeader(CompositionConstants.VERSION_UID, VersionUid.class);
        if (compositionVersionId == null) {
            throw new InternalErrorException("CompositionVersionId must not be null");
        }
        return compositionVersionId;
    }
}
