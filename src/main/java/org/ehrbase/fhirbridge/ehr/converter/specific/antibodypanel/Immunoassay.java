package org.ehrbase.fhirbridge.ehr.converter.specific.antibodypanel;

import org.hl7.fhir.r4.model.Observation;

public class Immunoassay {
    private final Observation observation;

    public Immunoassay(Observation observation) {
        this.observation = observation;
    }

    public Observation getObservation() {
        return observation;
    }

}
