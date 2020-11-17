package org.ehrbase.fhirbridge.fhir.procedure;

import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import org.hl7.fhir.r4.model.Procedure;
import org.openehealth.ipf.commons.ihe.fhir.AbstractPlainProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateProcedureProvider extends AbstractPlainProvider {

    private static final Logger LOG = LoggerFactory.getLogger(CreateProcedureProvider.class);

    @Create
    public MethodOutcome create(@ResourceParam Procedure procedure,
                                RequestDetails requestDetails,
                                HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse) {
        LOG.trace("Creating procedure...");

        return requestAction(procedure, null, httpServletRequest, httpServletResponse, requestDetails);
    }
}
