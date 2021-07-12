package org.ehrbase.fhirbridge.ehr.converter.specific.bloodgas;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Resource;

import java.util.Optional;

public class BloodGasPanel {

    private Optional<Observation> bloodGasPanel = Optional.empty();
    private Optional<Observation> pH = Optional.empty();
    private Optional<Observation> carbonDioxidePartialPressure = Optional.empty();
    private Optional<Observation> oxygenPartialPressure = Optional.empty();
    private Optional<Observation> oxygenSaturation = Optional.empty();

    public BloodGasPanel(Observation observation) {
        setObservations(observation);
    }


    private void setObservations(Observation observation) {
        bloodGasPanel = Optional.of(observation);
        for (Resource resource : observation.getContained()) {
            setProfiles(resource);
        }
    }

    // Implementing these as classes would be more appropriate
    private void setProfiles(Resource resource) {
        try {
            String profileUrl = resource.getMeta().getProfile().get(0).getValue();
            if (BloogGasProfileUrl.PH.getUrl().equals(profileUrl)) {
                this.pH = Optional.of((Observation) resource);
            } else if (BloogGasProfileUrl.CARBONDIOXIDE_PARTIAL_PRESSURE.getUrl().equals(profileUrl)) {
                this.carbonDioxidePartialPressure = Optional.of((Observation) resource);
            } else if (BloogGasProfileUrl.OXYGENPARTIAL_PRESSURE.getUrl().equals(profileUrl)) {
                this.oxygenPartialPressure = Optional.of((Observation) resource);
            } else if (BloogGasProfileUrl.OXYGEN_SATURATION.getUrl().equals(profileUrl)) {
                this.oxygenSaturation = Optional.of((Observation) resource);
            } else {
                throw new ConversionException("Blood gas panel bundle needs to contain only the profiles for the blood gas panel. Please delete profile " + profileUrl + " from the Bundle.");
            }

        } catch (IndexOutOfBoundsException e) {
            throw new ConversionException("Make sure only the for Blood Gas Panel supported Profiles are contained in the Bundle these are: blood gas panel, oxygen saturation, carbon dioxide saturation, ph, oxygen partaial pressure");
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
