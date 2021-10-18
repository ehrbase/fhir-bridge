package org.ehrbase.fhirbridge.ehr.converter.specific.antibodypanel;

import org.hl7.fhir.r4.model.Observation;

public class Immunoassay {
    private final Observation observation;
    private final Boolean hasValueQuantity;
    private final Boolean hasValueCodeableConcept;

    public Immunoassay(Observation observation, Boolean hasValueQuantity) {
        this.observation = observation;
        this.hasValueQuantity = hasValueQuantity;
        this.hasValueCodeableConcept = !hasValueQuantity;
    }

    public Observation getObservation() {
        return observation;
    }

    public Boolean getHasValueQuantity() {
        return hasValueQuantity;
    }

    public Boolean getHasValueCodeableConcept() {
        return hasValueCodeableConcept;
    }

}
