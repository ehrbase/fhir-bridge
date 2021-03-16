package org.ehrbase.fhirbridge.fhir.encounter;

import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import org.hl7.fhir.r4.model.Encounter;
import org.openehealth.ipf.commons.ihe.fhir.AbstractPlainProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Implementation of {@link org.openehealth.ipf.commons.ihe.fhir.FhirProvider FhirProvider} that provides REST support
 * for 'Create Encounter' transaction.
 *
 * @since 1.0.0
 */
public class CreateEncounterProvider extends AbstractPlainProvider {

    @Create
    public MethodOutcome createEncounter(@ResourceParam Encounter encounter, RequestDetails requestDetails,
                                           HttpServletRequest request, HttpServletResponse response) {
        return requestAction(encounter, null, request, response, requestDetails);
    }
}
