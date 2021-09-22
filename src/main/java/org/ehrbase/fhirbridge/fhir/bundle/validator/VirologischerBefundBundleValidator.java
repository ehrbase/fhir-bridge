package org.ehrbase.fhirbridge.fhir.bundle.validator;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.hl7.fhir.r4.model.Bundle;

import java.util.Map;


public class VirologischerBefundBundleValidator extends AbstractBundleValidator {

    private static final String VIROLOGISCHER_BEFUND_URL = "http://highmed.org/StructureDefinition/ic/VirologischerBefund";
    private static final String SPECIMEN_URL = "http://hl7.org/fhir/StructureDefinition/Specimen";
    private static final String DIAGNOSTIC_REPORT_URL ="http://highmed.org/fhir/StructureDefinition/ic/DiagnosticReportLab";

    private int virologischerBefundProfilesContained = 0;
    private int specimenProfilesContained = 0;
    private int diagnosticReportProfilesContained = 0;

    @Override
    public void validateRequest(Object bundle, Map<String, Object> parameters) {
        super.validateRequest(bundle, parameters);
        validateVirologischerBefund((Bundle) bundle);
    }

    private void validateVirologischerBefund(Bundle bundle) {
        resetAttributes();
        for (Bundle.BundleEntryComponent entry : bundle.getEntry()) {
            validateProfiles(entry);
        }
        checkIfAtLeastOneObservationContained();
    }


    private void resetAttributes() {
        virologischerBefundProfilesContained = 0;
        specimenProfilesContained = 0;
        diagnosticReportProfilesContained = 0;
    }

    private void validateProfiles(Bundle.BundleEntryComponent entry) {
        try {
            String profileUrl = entry.getResource().getMeta().getProfile().get(0).getValue();
            switch (profileUrl) {
                case VIROLOGISCHER_BEFUND_URL:
                    setVirologischerBefund();
                    break;
                case SPECIMEN_URL:
                    specimenProfilesContained += 1;
                    break;
                case DIAGNOSTIC_REPORT_URL:
                    diagnosticReportProfilesContained += 1;
                    break;
                default:
                    throw new UnprocessableEntityException("Virologischer Befund bundle needs to contain only the profiles for the Virologischer Befund. Please delete profile " + profileUrl + " from the Bundle.");
            }

        } catch (IndexOutOfBoundsException e) {
            throw new UnprocessableEntityException("Make sure only the for Virologischer Befund supported Profiles are contained in the Bundle.");
        }
    }



    private void checkIfAtLeastOneObservationContained() {
        if (!checkIfOneProfileIsPresent()) {
            throw new UnprocessableEntityException("Virologischer Befund needs to contain at least one profile of VirologischerBefund, DiagnosticReport and Specimen.");
        }
    }


    private boolean checkIfOneProfileIsPresent() {
        return virologischerBefundProfilesContained ==1 && specimenProfilesContained ==1 && diagnosticReportProfilesContained ==1;
    }

    private void setVirologischerBefund() {
        virologischerBefundProfilesContained += 1;
        if (virologischerBefundProfilesContained != 1) {
            throw new UnprocessableEntityException("Virologischer Befund profile is duplicated within the bundle, please delete one of them");
        }
    }


}
