package org.ehrbase.fhirbridge.fhir.bundle.validator;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.hl7.fhir.r4.model.Bundle;

import java.util.Map;

public class BloodGasPanelBundleValidator extends AbstractBundleValidator {
    private static final String BLOOD_GAS_URL = "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-gas-panel";
    private static final String PH_URL = "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/pH";
    private static final String CARBON_DIOXIDE_PARTIAL_PRESSURE_URL = "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/carbon-dioxide-partial-pressure";
    private static final String OXYGEN_PARTIAL_PRESSURE_URL = "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/oxygen-partial-pressure";
    private static final String OXYGEN_SATURATION_URL = "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/oxygen-saturation";

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
                case BLOOD_GAS_URL:
                    setBloodGasPanel();
                    break;
                case PH_URL:
                    phProfilesContained += 1;
                    break;
                case CARBON_DIOXIDE_PARTIAL_PRESSURE_URL:
                    carbonDioxideProfilesContained += 1;
                    break;
                case OXYGEN_PARTIAL_PRESSURE_URL:
                    oxygenPartialProfilesContained += 1;
                    break;
                case OXYGEN_SATURATION_URL:
                    oxygenSaturationProfilesContained += 1;
                    break;
                default:
                    throw new UnprocessableEntityException("Blood gas panel bundle needs to contain only the profiles for the blood gas panel. Please delete profile " + profileUrl + " from the Bundle.");
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
        return oxygenPartialProfilesContained == 1 || carbonDioxideProfilesContained == 1 || phProfilesContained == 1 || oxygenSaturationProfilesContained == 1;
    }

    private void setBloodGasPanel() {
        bloodGasProfilesContained += 1;
        if (bloodGasProfilesContained != 1) {
            throw new UnprocessableEntityException("Blood gas Panel profile is duplicated within the bundle, please delete one of them");
        }

    }


}

