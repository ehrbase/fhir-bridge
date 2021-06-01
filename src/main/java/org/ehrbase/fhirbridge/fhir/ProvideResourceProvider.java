package org.ehrbase.fhirbridge.fhir;

import ca.uhn.fhir.rest.annotation.ConditionalUrlParam;
import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.annotation.Update;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import org.hl7.fhir.r4.model.DomainResource;
import org.hl7.fhir.r4.model.IdType;
import org.openehealth.ipf.commons.ihe.fhir.AbstractPlainProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Implementation of {@link AbstractPlainProvider} that handles "Provide Resource" transaction
 * {@link Create} and {@link Update} operations.
 *
 * @since 1.2.0
 */
public class ProvideResourceProvider<T extends DomainResource> extends AbstractPlainProvider {

    private static final Logger LOG = LoggerFactory.getLogger(ProvideResourceProvider.class);

    /**
     * Provides resource using {@link Create} operation.
     *
     * @param resource       resource to be created
     * @param requestDetails FHIR request details
     * @param request        HTTP servlet request
     * @param response       HTTP servlet response
     * @return the result of the operation
     */
    @Create
    public MethodOutcome create(@ResourceParam T resource,
                                RequestDetails requestDetails,
                                HttpServletRequest request, HttpServletResponse response) {
        LOG.trace("Executing 'Provide {}' transaction using 'create' operation...", resource.getResourceType());
        return requestAction(resource, null, request, response, requestDetails);
    }

    /**
     * Provides resource using {@link Update} operation.
     *
     * @param resource       resource to be updated
     * @param id             ID of the existing resource
     * @param conditionalUrl conditional "search" URL for update
     * @param requestDetails FHIR request details
     * @param request        HTTP servlet request
     * @param response       HTTP servlet response
     * @return the outcome of the operation
     */
    @Update
    public MethodOutcome update(@ResourceParam T resource,
                                @IdParam IdType id,
                                @ConditionalUrlParam String conditionalUrl,
                                RequestDetails requestDetails,
                                HttpServletRequest request, HttpServletResponse response) {
        LOG.trace("Executing 'Provide {}' transaction using 'update' operation...", resource.getResourceType());
        return requestAction(resource, null, request, response, requestDetails);
    }
}
