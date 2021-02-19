package org.ehrbase.fhirbridge.ehr.converter.bloodgas;

import ca.uhn.fhir.rest.server.exceptions.InternalErrorException;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Resource;

import java.util.Optional;

public class BloodGasPanel {
    private static final String pHUrl = "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/pH";
    private static final String carbonDioxidePartialPressureUrl = "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/carbon-dioxide-partial-pressure";
    private static final String oxygenPartialPressureUrl = "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/oxygen-partial-pressure";
    private static final String oxygenSaturationUrl = "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/oxygen-saturation";

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
            switch (profileUrl) {
                case pHUrl:
                    this.pH = Optional.of((Observation) resource);
                    break;
                case carbonDioxidePartialPressureUrl:
                    this.carbonDioxidePartialPressure = Optional.of((Observation) resource);
                    break;
                case oxygenPartialPressureUrl:
                    this.oxygenPartialPressure = Optional.of((Observation) resource);
                    break;
                case oxygenSaturationUrl:
                    this.oxygenSaturation = Optional.of((Observation) resource);
                    break;
                default:
                    throw new UnprocessableEntityException("Blood gas panel bundle needs to contain only the profiles for the blood gas panel. Please delete profile " + profileUrl + " from the Bundle.");
            }

        } catch (IndexOutOfBoundsException e) {
            throw new UnprocessableEntityException("Make sure only the for Blood Gas Panel supported Profiles are contained in the Bundle these are: blood gas panel, oxygen saturation, carbon dioxide saturation, ph, oxygen partaial pressure");
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
