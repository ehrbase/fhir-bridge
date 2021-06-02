package org.ehrbase.fhirbridge.fhir.encounter;

import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.annotation.ConditionalUrlParam;
import ca.uhn.fhir.rest.annotation.Update;
import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Encounter;
import org.openehealth.ipf.commons.ihe.fhir.AbstractPlainProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of {@link org.openehealth.ipf.commons.ihe.fhir.FhirProvider FhirProvider} that provides REST support
 * for 'Create Encounter' transaction.
 *
 * @since 1.0.0
 */
public class ProvideEncounterProvider extends AbstractPlainProvider {

    private static final Logger LOG = LoggerFactory.getLogger(ProvideEncounterProvider.class);

    @Create
    public MethodOutcome createEncounter(@ResourceParam Encounter encounter,
                                         RequestDetails requestDetails,
                                         HttpServletRequest request,
                                         HttpServletResponse response) {
        LOG.trace("Executing 'Provide Encounter' transaction using 'create' operation...");
        return requestAction(encounter, null, request, response, requestDetails);
    }

    @Update
    public MethodOutcome update(@ResourceParam Encounter encounter,
                                @IdParam IdType id,
                                @ConditionalUrlParam String conditionalUrl,
                                RequestDetails requestDetails,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        LOG.trace("Executing 'Provide Encounter' transaction using 'update' operation...");
        return requestAction(encounter, null, request, response, requestDetails);
    }
}
