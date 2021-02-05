package org.ehrbase.fhirbridge.fhir.bundle.BundleValidator;

import ca.uhn.fhir.rest.server.exceptions.InternalErrorException;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Resource;

import java.util.Map;

public class BloodGasPanelBundleValidator extends AbstractBundleValidator {
    private static final String bloodGasUrl = "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-gas-panel";
    private static final String pHUrl = "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/pH";
    private static final String carbonDioxidePartialPressureUrl = "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/carbon-dioxide-partial-pressure";
    private static final String oxygenPartialPressureUrl = "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/oxygen-partial-pressure";
    private static final String oxygenSaturationUrl = "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/oxygen-saturation";

    private int bloodGasProfilesContained = 0;
    private int phProfilesContained = 0;
    private int carbonDioxideProfilesContained = 0;
    private int oxygenPartialProfilesContained = 0;
    private int oxygenSaturationProfilesContained = 0;

    @Override
    public void validateRequest(Object bundle, Map<String, Object> parameters) {
        super.validateRequest(bundle, parameters);
        validateBloodGasPanel((Bundle) bundle);
    }

    private void validateBloodGasPanel(Bundle bundle) {
        resetAttributes();
        for (Bundle.BundleEntryComponent entry : bundle.getEntry()) {
            validateProfiles(entry);
        }
        checkIfAtLeastOneObservationContained();
    }

    private void resetAttributes() {
        bloodGasProfilesContained = 0;
        phProfilesContained = 0;
        carbonDioxideProfilesContained = 0;
        oxygenPartialProfilesContained = 0;
        oxygenSaturationProfilesContained = 0;
    }

    private void validateProfiles(Bundle.BundleEntryComponent entry) {
        try {
            String profileUrl = entry.getResource().getMeta().getProfile().get(0).getValue();
            switch (profileUrl) {
                case bloodGasUrl:
                    setBloodGasPanel();
                    break;
                case pHUrl:
                    validatePhCount();
                    break;
                case carbonDioxidePartialPressureUrl:
                    validateCarbonDioxideCount();
                    break;
                case oxygenPartialPressureUrl:
                    validateOxygenPressureCount();
                    break;
                case oxygenSaturationUrl:
                    validateOxygenSaturationCount();
                    break;
                default:
                    throw new InternalErrorException("Blood gas panel bundle needs to contain only the profiles for the blood gas panel. Please delete profile " + profileUrl + " from the Bundle.");
            }

        } catch (IndexOutOfBoundsException e) {
            throw new UnprocessableEntityException("Make sure only the for Blood Gas Panel supported Profiles are contained in the Bundle these are: blood gas panel, oxygen saturation, carbon dioxide saturation, ph, oxygen partaial pressure");
        }
    }


    private void checkIfAtLeastOneObservationContained() {
        if (!checkIfOneProfileIsPresent()) {
            throw new UnprocessableEntityException("Bundle Blood gas panel needs to contain at least one of the following profiles: oxygen partial pressure, carbon dioxide partial pressure" +
                    ", ph or oxygen saturation");
        }
    }

    private boolean checkIfOneProfileIsPresent() {
        return oxygenPartialProfilesContained==1 || carbonDioxideProfilesContained==1|| phProfilesContained==1 || oxygenSaturationProfilesContained ==1;
    }

    private void setBloodGasPanel() {
        bloodGasProfilesContained += 1;
        if (bloodGasProfilesContained != 1) {
            throw new InternalErrorException("Blood gas Panel profile is duplicated within the bundle, please delete one of them");
        }

    }

    private void validatePhCount() {
        phProfilesContained += 1;
        if (phProfilesContained != 1) {
            throw new InternalErrorException("PH profile is duplicated within the bundle, please delete one of them");
        }
    }

    private void validateCarbonDioxideCount() {
        carbonDioxideProfilesContained += 1;
        if (carbonDioxideProfilesContained != 1) {
            throw new InternalErrorException("Carbon Dioxide Partial Pressure profile is duplicated within the bundle, please delete one of them");
        }

    }

    private void validateOxygenPressureCount() {
        oxygenPartialProfilesContained += 1;
        if (oxygenPartialProfilesContained != 1) {
            throw new InternalErrorException("Oxygen partial pressure profile is duplicated within the bundle, please delete one of them");
        }

    }

    private void validateOxygenSaturationCount() {
        oxygenSaturationProfilesContained += 1;
        if (oxygenSaturationProfilesContained != 1) {
            throw new InternalErrorException("Oxygen saturation profile is duplicated within the bundle, please delete one of them");

        }
    }

}

