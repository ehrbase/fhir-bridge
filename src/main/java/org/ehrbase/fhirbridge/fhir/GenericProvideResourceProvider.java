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
 * Generic implementation of {@link AbstractPlainProvider} that handles 'Provide Resource' transaction
 * using {@link Create} and {@link Update} operations.
 *
 * @since 1.2.0
 */
public class GenericProvideResourceProvider<T extends DomainResource> extends AbstractPlainProvider {

    private static final Logger LOG = LoggerFactory.getLogger(GenericProvideResourceProvider.class);

    @Create
    public MethodOutcome create(@ResourceParam T resource,
                                RequestDetails requestDetails,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        LOG.trace("Executing 'Provide resource (resourceType: {})' transaction using 'create' operation...",
                resource.getResourceType());

        return requestAction(resource, null, request, response, requestDetails);
    }

    @Update
    public MethodOutcome update(@ResourceParam T resource,
                                @IdParam IdType id,
                                @ConditionalUrlParam String conditionalUrl,
                                RequestDetails requestDetails,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        LOG.trace("Executing 'Provide resource (resourceType: {})' transaction using 'update' operation...",
                resource.getResourceType());

        return requestAction(resource, null, request, response, requestDetails);
    }
}
