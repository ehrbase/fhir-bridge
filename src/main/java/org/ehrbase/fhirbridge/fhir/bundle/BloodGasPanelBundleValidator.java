package org.ehrbase.fhirbridge.fhir.bundle;

import ca.uhn.fhir.rest.server.exceptions.InternalErrorException;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Resource;

import java.util.Map;
import java.util.Optional;

public class BloodGasPanelBundleValidator extends AbstractBundleValidator {
    private static final String bloodGasUrl = "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-gas-panel";
    private static final String pHUrl = "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/pH";
    private static final String carbonDioxidePartialPressureUrl = "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/carbon-dioxide-partial-pressure";
    private static final String oxygenPartialPressureUrl = "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/oxygen-partial-pressure";
    private static final String oxygenSaturationUrl = "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/oxygen-saturation";

    private Optional<Observation> bloodGasPanel = Optional.empty();
    private Optional<Observation> pH = Optional.empty();
    private Optional<Observation> carbonDioxidePartialPressure = Optional.empty();
    private Optional<Observation> oxygenPartialPressure = Optional.empty();
    private Optional<Observation> oxygenSaturation = Optional.empty();

    private int bloodGasProfilesContained = 0;
    private int phProfilesContained = 0;
    private int carbonDioxideProfilesContained = 0;
    private int oxygenPartialProfilesContained = 0;
    private int oxygenSaturationProfilesContained = 0;

    private Bundle bundle;

    @Override
    public void validateRequest(Object payload, Map<String, Object> parameters) {
        super.validateRequest(payload, parameters);
        this.bundle = (Bundle) payload;
        validateObservations();
    }

    private void validateObservations() {
        resetAttributes();
        for (Bundle.BundleEntryComponent entry : bundle.getEntry()) {
            validateProfiles(entry);
        }
        checkIfObservationsComplete();
    }


    private void resetAttributes() {
        bloodGasPanel = Optional.empty();
        pH = Optional.empty();
        carbonDioxidePartialPressure = Optional.empty();
        oxygenPartialPressure = Optional.empty();
        oxygenSaturation = Optional.empty();
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
                    setBloodGasPanel(entry.getResource());
                    break;
                case pHUrl:
                    setpH(entry.getResource());
                    break;
                case carbonDioxidePartialPressureUrl:
                    setCarbonDioxidePartialPressure(entry.getResource());
                    break;
                case oxygenPartialPressureUrl:
                    setOxygenPartialPressure(entry.getResource());
                    break;
                case oxygenSaturationUrl:
                    setOxygenSaturation(entry.getResource());
                    break;
                default:
                    throw new InternalErrorException("Blood gas panel bundle needs to contain only the profiles for the blood gas panel. Please delete profile " + profileUrl + " from the Bundle.");
            }

        } catch (IndexOutOfBoundsException e) {
            throw new UnprocessableEntityException("Make sure only the for Blood Gas Panel supported Profiles are contained in the Bundle these are: blood gas panel, oxygen saturation, carbon dioxide saturation, ph, oxygen partaial pressure");
        }
    }


    private void checkIfObservationsComplete() {
        if (!checkIfOneProfileIsPresent()) {
            throw new UnprocessableEntityException("Bundle Blood gas panel needs to contain at least one of the following profiles: oxygen partial pressure, carbon dioxide partial pressure" +
                    ", ph or oxygen saturation");
        }
    }

    private boolean checkIfOneProfileIsPresent() {
        return oxygenPartialPressure.isPresent() || carbonDioxidePartialPressure.isPresent() || pH.isPresent() || oxygenSaturation.isPresent();
    }

    private void setBloodGasPanel(Resource resource) {
        bloodGasProfilesContained += 1;
        if (bloodGasProfilesContained == 1) {
            this.bloodGasPanel = Optional.of((Observation) resource);
        } else {
            throw new InternalErrorException("Blood gas Panel profile is duplicated within the bundle, please delete one of them");
        }

    }

    private void setpH(Resource resource) {
        phProfilesContained += 1;
        if (phProfilesContained == 1) {
            this.pH = Optional.of((Observation) resource);
        } else {
            throw new InternalErrorException("PH profile is duplicated within the bundle, please delete one of them");
        }
    }

    private void setCarbonDioxidePartialPressure(Resource resource) {
        carbonDioxideProfilesContained += 1;
        if (carbonDioxideProfilesContained == 1) {
            this.carbonDioxidePartialPressure = Optional.of((Observation) resource);
        } else {
            throw new InternalErrorException("Carbon Dioxide Partial Pressure profile is duplicated within the bundle, please delete one of them");
        }

    }

    private void setOxygenPartialPressure(Resource resource) {
        oxygenPartialProfilesContained += 1;
        if (oxygenPartialProfilesContained == 1) {
            this.oxygenPartialPressure = Optional.of((Observation) resource);
        } else {
            throw new InternalErrorException("Oxygen partial pressure profile is duplicated within the bundle, please delete one of them");
        }

    }

    private void setOxygenSaturation(Resource resource) {
        oxygenSaturationProfilesContained += 1;
        if (oxygenSaturationProfilesContained == 1) {
            this.oxygenSaturation = Optional.of((Observation) resource);
        } else {
            throw new InternalErrorException("Oxygen saturation profile is duplicated within the bundle, please delete one of them");

        }
    }

    public Observation getBloodGasPanel() {
        return bloodGasPanel.get();
    }

    public Optional<Observation> getpH() {
        return pH;
    }

    public Optional<Observation> getCarbonDioxidePartialPressure() {
        return carbonDioxidePartialPressure;
    }

    public Optional<Observation> getOxygenPartialPressure() {
        return oxygenPartialPressure;
    }

    public Optional<Observation> getOxygenSaturation() {
        return oxygenSaturation;
    }

}

