package org.ehrbase.fhirbridge.fhir.procedure;

import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Procedure;
import org.openehealth.ipf.commons.ihe.fhir.AbstractPlainProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReadProcedureProvider extends AbstractPlainProvider {

    @Read
    public Procedure read(@IdParam IdType id, RequestDetails requestDetails,
                          HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return requestResource(id, null, Procedure.class, httpServletRequest, httpServletResponse, requestDetails);
    }
}
