package org.ehrbase.fhirbridge.fhir.bundle.converter;

import org.ehrbase.fhirbridge.fhir.common.Profile;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.springframework.lang.NonNull;

public class DiagnosticReportLabConverter extends AbstractBundleConverter<DiagnosticReport> {

    @Override
    public DiagnosticReport convert(@NonNull Bundle bundle) {
        return getRoot(bundle, Profile.DIAGNOSTIC_REPORT_LAB);
    }
}
