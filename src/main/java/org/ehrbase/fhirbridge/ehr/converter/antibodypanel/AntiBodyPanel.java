package org.ehrbase.fhirbridge.ehr.converter.antibodypanel;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Resource;

import java.util.Optional;

public class AntiBodyPanel {

    private Optional<Observation> antiBodyPanel = Optional.empty();
    private Optional<Observation> aBPresence = Optional.empty();
    private Optional<Observation> aBUnitsVolume = Optional.empty();
    private Optional<Observation> igAAbPresence = Optional.empty();
    private Optional<Observation> igAAbUnitVolume = Optional.empty();
    private Optional<Observation> igMAbPresence = Optional.empty();
    private Optional<Observation> igMAbUnitsVolume = Optional.empty();
    private Optional<Observation> igGAbPresence = Optional.empty();
    private Optional<Observation> igGAbUnitsVolume = Optional.empty();

    public AntiBodyPanel(Observation observation) {
        setObservations(observation);
    }

    private void setObservations(Observation observation) {
        antiBodyPanel = Optional.of(observation);
        for (Resource resource : observation.getContained()) {
            setProfiles(resource);
        }
    }

    private void setProfiles(Resource resource) {
        try {
            String profileUrl = resource.getMeta().getProfile().get(0).getValue();
            if (AntiBodyProfileUrl.AB_PRESENCE.getUrl().equals(profileUrl)) {
                this.aBPresence = Optional.of((Observation) resource);

            } else if (AntiBodyProfileUrl.AB_UNITS_VOLUME.getUrl().equals(profileUrl)) {
                this.aBUnitsVolume = Optional.of((Observation) resource);

            } else if (AntiBodyProfileUrl.IGA_AB_PRESENCE.getUrl().equals(profileUrl)) {
                this.igAAbPresence = Optional.of((Observation) resource);

            } else if (AntiBodyProfileUrl.IGA_AB_UNITS_VOLUME.getUrl().equals(profileUrl)) {
                this.igAAbUnitVolume = Optional.of((Observation) resource);

            } else if (AntiBodyProfileUrl.IGM_AB_PRESENCE.getUrl().equals(profileUrl)) {
                this.igMAbPresence = Optional.of((Observation) resource);

            } else if (AntiBodyProfileUrl.IGM_AB_UNITS_VOLUME.getUrl().equals(profileUrl)) {
                this.igMAbUnitsVolume = Optional.of((Observation) resource);

            } else if (AntiBodyProfileUrl.IGG_AB_PRESENCE.getUrl().equals(profileUrl)) {
                this.igGAbPresence = Optional.of((Observation) resource);

            } else if (AntiBodyProfileUrl.IGG_AB_UNITS_VOLUME.getUrl().equals(profileUrl)) {
                this.igGAbUnitsVolume = Optional.of((Observation) resource);

            } else {
                throw new UnprocessableEntityException("Anti body panel bundle needs to contain only the profiles for the Anti body panel. Please delete profile " + profileUrl + " from the Bundle.");
            }

        } catch (IndexOutOfBoundsException e) {
            throw new UnprocessableEntityException("Make sure only the for Anti body panel supported Profiles are contained in the Bundle");
        }
    }

    public Observation getAntiBodyPanel() {
        return antiBodyPanel.get();
    }

    public Optional<Observation> getaBPresence() {
        return aBPresence;
    }

    public Optional<Observation> getaBUnitsVolume() {
        return aBUnitsVolume;
    }

    public Optional<Observation> getIgAAbPresence() {
        return igAAbPresence;
    }

    public Optional<Observation> getIgAAbUnitVolume() {
        return igAAbUnitVolume;
    }

    public Optional<Observation> getIgMAbPresence() {
        return igMAbPresence;
    }

    public Optional<Observation> getIgMAbUnitsVolume() {
        return igMAbUnitsVolume;
    }

    public Optional<Observation> getIgGAbPresence() {
        return igGAbPresence;
    }

    public Optional<Observation> getIgGAbUnitsVolume() {
        return igGAbUnitsVolume;
    }
}
