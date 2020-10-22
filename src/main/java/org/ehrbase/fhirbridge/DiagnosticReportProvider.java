package org.ehrbase.fhirbridge;

import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.api.MethodOutcome;
import org.hl7.fhir.instance.model.api.IIdType;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.openehealth.ipf.commons.ihe.fhir.AbstractPlainProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DiagnosticReportProvider extends AbstractPlainProvider {

    @Create
    public MethodOutcome create(@ResourceParam DiagnosticReport diagnosticReport, HttpServletRequest request, HttpServletResponse response) {
        return requestAction(diagnosticReport, null, request, response, null);
    }

    @Read
    public DiagnosticReport read(@IdParam IIdType id, HttpServletRequest request, HttpServletResponse response) {
        return requestResource(null, null, DiagnosticReport.class, request, response, null);
    }
}
