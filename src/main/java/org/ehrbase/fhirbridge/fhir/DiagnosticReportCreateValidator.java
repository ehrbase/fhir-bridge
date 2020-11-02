package org.ehrbase.fhirbridge.fhir;

import ca.uhn.fhir.context.FhirContext;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class DiagnosticReportCreateValidator extends FhirTransactionValidator.Support {

    private final Logger logger = LoggerFactory.getLogger(DiagnosticReportCreateValidator.class);

    public DiagnosticReportCreateValidator(FhirContext context) {
    }

    @Override
    public void validateRequest(Object payload, Map<String, Object> parameters) {
        logger.debug("Validate incoming request");
    }
}
